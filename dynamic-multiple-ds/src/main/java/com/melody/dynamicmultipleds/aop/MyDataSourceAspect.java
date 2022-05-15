package com.melody.dynamicmultipleds.aop;

import com.melody.dynamicmultipleds.annotation.MyDataSource;
import com.melody.dynamicmultipleds.holder.DynamicMultipleDataSourceContextHolder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Order(1)
@Aspect
@Component
public class MyDataSourceAspect {

    @Pointcut("@annotation(com.melody.dynamicmultipleds.annotation.MyDataSource)"
    +"||@within(com.melody.dynamicmultipleds.annotation.MyDataSource)")
    public void myDS(){};

    @Around("myDS()")
    public Object around(ProceedingJoinPoint point)throws Throwable {
        MethodSignature signature = (MethodSignature)point.getSignature();
        MyDataSource myDataSource = AnnotationUtils.findAnnotation(signature.getMethod(), MyDataSource.class);
        if(Objects.nonNull(myDataSource)){
            DynamicMultipleDataSourceContextHolder.setDataSourceName(myDataSource.dataSourceName());
        }
        try{
            return point.proceed();
        } finally {
            //清空数据源
            DynamicMultipleDataSourceContextHolder.clearDataSourceName();
        }
    }
}
