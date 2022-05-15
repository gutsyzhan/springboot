package com.melody.dynamicmultipleds.mydatasource;

import com.melody.dynamicmultipleds.holder.DynamicMultipleDataSourceContextHolder;
import com.melody.dynamicmultipleds.provider.YmlMultipleDataSourceProvider;
import com.melody.dynamicmultipleds.provider.MultipleDataSourceProvider;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.HashMap;
import java.util.Map;

/**
 * 动态使用数据源
 */
public class DynamicMultipleDataSource extends AbstractRoutingDataSource {
    //实际数据源提供者
    YmlMultipleDataSourceProvider ymlMultipleDataSourceProvider;

    public DynamicMultipleDataSource(YmlMultipleDataSourceProvider provider){
        this.ymlMultipleDataSourceProvider = provider;
        Map<Object, Object> targetDataSources = new HashMap<>(provider.loadDataSource());
        //设置所有的数据源
        super.setTargetDataSources(targetDataSources);
        //设置默认的数据源（即没有使用@MyDataSource注解时，使用的默认数据源）
        super.setDefaultTargetDataSource(provider.loadDataSource().get(MultipleDataSourceProvider.DEFAULT_DATASOURCE));
        super.afterPropertiesSet();
    }

    /**
     * 当系统需要获取数据源时，会自动调用该方法来获取数据源的名称
     * @return 数据源名称
     */
    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicMultipleDataSourceContextHolder.getDataSourceName();
    }
}
