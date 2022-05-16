//package com.melody.limitredis.aspect;
//
//import com.melody.limitredis.annotations.RateLimiter;
//import com.melody.limitredis.enums.LimitType;
//import com.melody.limitredis.exception.BizException;
//import com.melody.limitredis.utils.IpUtils;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.Signature;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.annotation.Pointcut;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.core.script.RedisScript;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import java.lang.reflect.Method;
//import java.util.Collections;
//import java.util.List;
//
//@Component
//@Aspect
//public class RateLimiterAspect {
//    private static final Logger logger = LoggerFactory.getLogger(RateLimiterAspect.class);
//
//    @Autowired
//    private RedisTemplate<Object,Object> redisTemplate;
//
//    @Autowired
//    private RedisScript<Long> limitScript;
//
//    @Before("@annotation(rateLimiter)")
//    public void before(JoinPoint point, RateLimiter rateLimiter){
//        int time = rateLimiter.time();
//        int count = rateLimiter.count();
//
//        String combineKey = getCombineKey(rateLimiter,point);
//        List<Object> keys = Collections.singletonList(combineKey);
//        try{
//            Long number = redisTemplate.execute(limitScript, keys, count, time);
//            if(number == null || number.intValue() > count){
//                throw new BizException("请求过于频繁，请稍后重试",500);
//            }
//            logger.info("当前请求次数'{}',限定次数'{}'", number.intValue(), count);
//        }catch (BizException e){
//            throw e;
//        }catch (Exception e){
//            throw new RuntimeException("服务器限流异常，请稍候再试");
//        }
//    }
//
//    private String getCombineKey(RateLimiter rateLimiter, JoinPoint point) {
//        StringBuffer stringBuffer = new StringBuffer(rateLimiter.key());
//        //IP限制
//        if(rateLimiter.limitType() == LimitType.IP){
//            stringBuffer.append(IpUtils.getRequestIp(((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest())).append("-");
//        }
//        MethodSignature signature = (MethodSignature)point.getSignature();
//        Method method = signature.getMethod();
//        Class<?> targetClass = method.getDeclaringClass();
//        stringBuffer.append(targetClass.getName()).append("-").append(method.getName());
//        return stringBuffer.toString();
//    }
//}
