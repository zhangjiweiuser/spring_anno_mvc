package com.zhang.jiwei.dao;

import com.zhang.jiwei.config.DataSourceType;
import com.zhang.jiwei.datasource.DataSources;
import com.zhang.jiwei.entity.Role;
import org.apache.ibatis.annotations.Param;

/**
 * Created by zhangjiwei on 2018/4/1.
 */
@DataSourceType(value = DataSources.SLAVE)
public interface RoleDao {

    Role getRoleById(@Param("id") int id);

    int insertRole(@Param("role") Role role);
}
