package com.melody.dynamicmultipleds.holder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 数据源切换工具类
 */
public class DynamicMultipleDataSourceContextHolder {
    public static final Logger log = LoggerFactory.getLogger(DynamicMultipleDataSourceContextHolder.class);

    private String dataSourceName;

    private static final ThreadLocal<String> CURRENT_DATASOURCE_NAME = new ThreadLocal<>();

    public static void setDataSourceName(String dataSourceName){
        log.info("切换到{}数据源",dataSourceName);
        CURRENT_DATASOURCE_NAME.set(dataSourceName);
    }

    public static String getDataSourceName(){
        return CURRENT_DATASOURCE_NAME.get();
    }

    public static void clearDataSourceName(){
        CURRENT_DATASOURCE_NAME.remove();
    }
}
