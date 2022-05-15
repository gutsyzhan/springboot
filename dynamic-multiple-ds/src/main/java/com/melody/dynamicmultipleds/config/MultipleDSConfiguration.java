package com.melody.dynamicmultipleds.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@ConfigurationProperties(prefix = "spring.datasource")
public class MultipleDSConfiguration {
    private Map<String,Map<String,String>> myDS;
    private int initialSize;
    private int minIdle;
    private int maxActive;
    private int maxWait;
    private int timeBetweenEvictionRunsMillis;
    private int minEvictableIdleTimeMillis;
    private int maxEvictableIdleTimeMillis;
    private String validationQuery;
    private boolean testOnBorrow;
    private boolean testOnReturn;
    private boolean testWhileIdle;

    public DruidDataSource dataSource(DruidDataSource druidDataSource){
        // 初始连接数
        druidDataSource.setInitialSize(initialSize);
        // 最小连接池数量
        druidDataSource.setMinIdle(minIdle);
        // 最大连接池数量
        druidDataSource.setMaxActive(maxActive);
        // 获取连接等待超时的时间
        druidDataSource.setMaxWait(maxWait);
        // 检测间隔时间，检测需要关闭的空闲连接，单位毫秒
        druidDataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        // 一个连接在连接池中最小的生存时间，单位毫秒
        druidDataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        // 一个连接在连接池中最大的生存时间，单位毫秒
        druidDataSource.setMaxEvictableIdleTimeMillis(maxEvictableIdleTimeMillis);
        // 配置检测连接是否有效
        druidDataSource.setValidationQuery(validationQuery);
        // 如果为true（默认为false），当应用向连接池申请连接时，连接池会判断这条连接是否是可用的
        druidDataSource.setTestOnBorrow(testOnBorrow);
        // 连接返回检测
        druidDataSource.setTestOnReturn(testOnReturn);
        // 失效连接检测
        druidDataSource.setTestWhileIdle(testWhileIdle);
        return druidDataSource;
    }

    public Map<String, Map<String, String>> getMyDS() {
        return myDS;
    }

    public void setMyDS(Map<String, Map<String, String>> myDS) {
        this.myDS = myDS;
    }

    public int getInitialSize() {
        return initialSize;
    }

    public void setInitialSize(int initialSize) {
        this.initialSize = initialSize;
    }

    public int getMinIdle() {
        return minIdle;
    }

    public void setMinIdle(int minIdle) {
        this.minIdle = minIdle;
    }

    public int getMaxActive() {
        return maxActive;
    }

    public void setMaxActive(int maxActive) {
        this.maxActive = maxActive;
    }

    public int getMaxWait() {
        return maxWait;
    }

    public void setMaxWait(int maxWait) {
        this.maxWait = maxWait;
    }

    public int getTimeBetweenEvictionRunsMillis() {
        return timeBetweenEvictionRunsMillis;
    }

    public void setTimeBetweenEvictionRunsMillis(int timeBetweenEvictionRunsMillis) {
        this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
    }

    public int getMinEvictableIdleTimeMillis() {
        return minEvictableIdleTimeMillis;
    }

    public void setMinEvictableIdleTimeMillis(int minEvictableIdleTimeMillis) {
        this.minEvictableIdleTimeMillis = minEvictableIdleTimeMillis;
    }

    public int getMaxEvictableIdleTimeMillis() {
        return maxEvictableIdleTimeMillis;
    }

    public void setMaxEvictableIdleTimeMillis(int maxEvictableIdleTimeMillis) {
        this.maxEvictableIdleTimeMillis = maxEvictableIdleTimeMillis;
    }

    public String getValidationQuery() {
        return validationQuery;
    }

    public void setValidationQuery(String validationQuery) {
        this.validationQuery = validationQuery;
    }

    public boolean isTestOnBorrow() {
        return testOnBorrow;
    }

    public void setTestOnBorrow(boolean testOnBorrow) {
        this.testOnBorrow = testOnBorrow;
    }

    public boolean isTestOnReturn() {
        return testOnReturn;
    }

    public void setTestOnReturn(boolean testOnReturn) {
        this.testOnReturn = testOnReturn;
    }

    public boolean isTestWhileIdle() {
        return testWhileIdle;
    }

    public void setTestWhileIdle(boolean testWhileIdle) {
        this.testWhileIdle = testWhileIdle;
    }
}
