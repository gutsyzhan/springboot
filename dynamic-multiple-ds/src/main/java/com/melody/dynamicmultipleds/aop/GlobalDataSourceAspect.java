package com.melody.dynamicmultipleds.aop;

import com.melody.dynamicmultipleds.holder.DynamicMultipleDataSourceContextHolder;
import com.melody.dynamicmultipleds.provider.MultipleDataSourceProvider;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

@Aspect
@Component
@Order(1)
public class GlobalDataSourceAspect {
    @Autowired
    private HttpSession session;

    /**
     * 拦截service包下面的所有类的所有方法
     */
    @Pointcut("execution(* com.melody.dynamicmultipleds.service.*.*(..))")
    public void globalDS(){};

    @Around("globalDS()")
    public Object around(ProceedingJoinPoint point){
        String dataSourceName = (String)session.getAttribute(MultipleDataSourceProvider.DS_SESSION_KEY);
        DynamicMultipleDataSourceContextHolder.setDataSourceName(dataSourceName);
        try {
            return point.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }finally {
            //清空数据源
            DynamicMultipleDataSourceContextHolder.clearDataSourceName();
        }
        return null;
    }
}
