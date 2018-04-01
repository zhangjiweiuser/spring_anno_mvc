package com.zhang.jiwei.controller;

import com.zhang.jiwei.entity.Role;
import com.zhang.jiwei.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhangjiwei on 2018/4/1.
 */
@RestController
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/getRoleById")
    public Role getRoleById(int id){
        return roleService.getRoleById(id);
    }

    @RequestMapping(value = "/insertRoleById")
    public int insertRoleById(Role role){
        return roleService.insertRole(role);
    }
}
