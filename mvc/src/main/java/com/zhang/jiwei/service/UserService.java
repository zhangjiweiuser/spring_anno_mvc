package com.zhang.jiwei.service;

import com.zhang.jiwei.entity.User;

/**
 * @author jiwei.zhang
 * @DATE 2017/12/18 0018
 */
public interface UserService {

    String getUserNameById(int id);

    User getUserById(int id);

    int update(String name);
}
