package com.melody.dynamicmultipleds.provider;

import javax.sql.DataSource;
import java.util.Map;

public interface MultipleDataSourceProvider {
    String DEFAULT_DATASOURCE = "master";

    //作为Session的存储key
    String DS_SESSION_KEY = "ds_session_key";

    Map<String, DataSource> loadDataSource();
}