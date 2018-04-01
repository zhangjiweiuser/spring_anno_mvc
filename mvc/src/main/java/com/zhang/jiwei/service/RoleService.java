package com.zhang.jiwei.service;

import com.zhang.jiwei.entity.Role;

/**
 * Created by zhangjiwei on 2018/4/1.
 */
public interface RoleService {
    Role getRoleById(int id);

    int insertRole( Role role);
}
