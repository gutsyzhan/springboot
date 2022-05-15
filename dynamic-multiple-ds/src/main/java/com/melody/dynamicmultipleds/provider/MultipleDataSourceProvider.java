package com.melody.dynamicmultipleds.provider;

import javax.sql.DataSource;
import java.util.Map;

public interface MultipleDataSourceProvider {
    String DEFAULT_DATASOURCE = "master";

    Map<String, DataSource> loadDataSource();
}