package com.zhang.jiwei.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class RoutingDataSource extends AbstractRoutingDataSource {

    private static final ThreadLocal<String> dataSourceKey = new ThreadLocal<>();

    public static void setThreadLocalDataSourceKey(String dataSourceKey) {
        RoutingDataSource.dataSourceKey.set(dataSourceKey);
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return dataSourceKey.get();
    }
}
