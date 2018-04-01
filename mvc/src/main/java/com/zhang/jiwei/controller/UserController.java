package com.zhang.jiwei.controller;

import com.zhang.jiwei.entity.User;
import com.zhang.jiwei.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author jiwei.zhang
 * @DATE 2017/12/18 0018
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(path = "/getUserNameById")
    @ResponseBody
    public String getUserNameById(int id) {
        return userService.getUserNameById(id);
    }

    @RequestMapping(path = "/updateUser")
    @ResponseBody
    public User getUserNameById(User user) {
        return user;
    }

    @RequestMapping(path = "/getUserById")
    @ResponseBody
    public User getUserById(int id) {
        String userName = userService.getUserNameById(id);
        return new User(id,userName,19);
    }

    @RequestMapping(path = "/getUserByName")
    @ResponseBody
    public User getUserByName(String name) {
        return new User(6,name,19);
    }
}
