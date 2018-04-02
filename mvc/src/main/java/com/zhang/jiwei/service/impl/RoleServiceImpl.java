package com.zhang.jiwei.service.impl;

import com.zhang.jiwei.slavedao.RoleDao;
import com.zhang.jiwei.entity.Role;
import com.zhang.jiwei.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhangjiwei on 2018/4/1.
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public Role getRoleById(int id) {
        return roleDao.getRoleById(id);
    }

    @Override
    public int insertRole(Role role) {
        return roleDao.insertRole(role);
    }
}
