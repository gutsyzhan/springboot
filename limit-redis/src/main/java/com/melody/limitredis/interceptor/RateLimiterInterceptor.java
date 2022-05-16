package com.melody.limitredis.interceptor;

import com.melody.limitredis.enums.LimitType;
import com.melody.limitredis.annotations.RateLimiter;
import com.melody.limitredis.utils.IpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

@Component
public class RateLimiterInterceptor implements HandlerInterceptor {
    public static final String LIMIT_IP_REQUEST = "limit_ip_request:";

    private static final Logger logger = LoggerFactory.getLogger(RateLimiterInterceptor.class);

    @Autowired
    private RedisTemplate<Object,Object> redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(handler instanceof HandlerMethod){
            HandlerMethod method = (HandlerMethod) handler;
            RateLimiter rateLimiter = method.getMethodAnnotation(RateLimiter.class);
            if(null ==rateLimiter)return true;
            String key = rateLimiter.key();
            int time = rateLimiter.time();
            int count = rateLimiter.count();
            LimitType limitType = rateLimiter.limitType();

            String ip = IpUtils.getRequestIp(request);
            String uri = request.getRequestURI();
            String redisKey = LIMIT_IP_REQUEST + uri + ":" + ip;

            //定义一个opsForValue操作
            ValueOperations<Object, Object> operation = redisTemplate.opsForValue();

            Integer number = null;
            boolean flag = operation.get(key) != null;
            if(flag){
                number = Integer.valueOf(operation.get(redisKey).toString());
            }
            //第一次访问则记录到Redis，第二次则累加
            if(null == number){
                operation.setIfAbsent(redisKey,1, time, TimeUnit.SECONDS);
            }else if(number < count){
                operation.increment(redisKey,1);
            }else{
                logger.info("【请求超过限定次数】用户IP[{}]访问地址[{}]超过了限定的次数[{}}]",ip,uri,count);
                Long earnTime = operation.getOperations().getExpire(redisKey)/1000+1;;
                return setResponse("请求过于频繁，请"+earnTime+"秒后再试！", response);
            }
            return true;
        }
        return true;
    }

    private boolean setResponse(String message, HttpServletResponse response) throws IOException {
        ServletOutputStream outputStream = null;
        try {
            response.setHeader("Content-type", "application/json; charset=utf-8");
            outputStream = response.getOutputStream();
            outputStream.write(message.getBytes(StandardCharsets.UTF_8));
        } catch (Exception ex) {
            return false;
        } finally {
            if (outputStream != null) {
                outputStream.flush();
                outputStream.close();
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
