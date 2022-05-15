package com.melody.dynamicmultipleds.provider;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.melody.dynamicmultipleds.config.MultipleDSConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class YmlMultipleDataSourceProvider implements MultipleDataSourceProvider{
    @Autowired
    private MultipleDSConfiguration multipleDSConfiguration;

    @Override
    public Map<String, DataSource> loadDataSource() {
        Map<String, Map<String, String>> myDS = multipleDSConfiguration.getMyDS();
        Map<String,DataSource> map = new HashMap<>(myDS.size());
        try{
            for (String key: myDS.keySet()){
                DruidDataSource druidDataSource = (DruidDataSource)DruidDataSourceFactory.createDataSource(myDS.get(key));
                map.put(key,multipleDSConfiguration.dataSource(druidDataSource));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }
}
