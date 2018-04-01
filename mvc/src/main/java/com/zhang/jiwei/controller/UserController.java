package com.zhang.jiwei.controller;

import com.zhang.jiwei.entity.User;
import com.zhang.jiwei.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jiwei.zhang
 * @DATE 2017/12/18 0018
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(path = "/getUserNameById")
    public String getUserNameById(int id) {
        return userService.getUserNameById(id);
    }

    @RequestMapping(path = "/updateUser")
    public User getUserNameById(User user) {
        return user;
    }

    @RequestMapping(path = "/getUserById")
    public User getUserById(int id) {
        return userService.getUserById(id);
    }

    @RequestMapping(path = "/getUserByName")
    public User getUserByName(String name) {
        return new User(6,name,19);
    }

    @RequestMapping(path = "/update")
    public int update(String name){
        return userService.update(name);
    }
}
