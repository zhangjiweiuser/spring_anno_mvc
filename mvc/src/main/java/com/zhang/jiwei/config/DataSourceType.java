package com.zhang.jiwei.config;

import com.zhang.jiwei.datasource.DataSources;

import java.lang.annotation.*;

/**
 * Created by zhangjiwei on 2018/4/1.
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.METHOD})
public @interface DataSourceType {
    DataSources value();
}
