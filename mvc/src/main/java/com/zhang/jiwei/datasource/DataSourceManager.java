package com.zhang.jiwei.datasource;

/**
 * Created by zhangjiwei on 2018/4/1.
 */
public class DataSourceManager {

    //    private static final ThreadLocal<DataSources> dataSources = new ThreadLocal<>();
    private static final ThreadLocal<DataSources> dataSources = new ThreadLocal() {
        @Override
        protected DataSources initialValue() {
            return DataSources.MASTER;
        }
    };

    public static DataSources get() {
        return dataSources.get();
    }

    public static void set(DataSources dataSourcesType) {
        dataSources.set(dataSourcesType);
    }
}
