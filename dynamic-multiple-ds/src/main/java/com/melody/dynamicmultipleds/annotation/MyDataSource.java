package com.melody.dynamicmultipleds.annotation;

import com.melody.dynamicmultipleds.provider.MultipleDataSourceProvider;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标记使用数据源的名称
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface MyDataSource {

    String dataSourceName() default MultipleDataSourceProvider.DEFAULT_DATASOURCE;

    @AliasFor("dataSourceName")
    String value() default MultipleDataSourceProvider.DEFAULT_DATASOURCE;
}
