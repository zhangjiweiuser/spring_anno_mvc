package com.zhang.jiwei.service.impl;

import com.zhang.jiwei.dao.UserDao;
import com.zhang.jiwei.entity.User;
import com.zhang.jiwei.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author jiwei.zhang
 * @DATE 2017/12/18 0018
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;

    public String getUserNameById(int id) {
        User user = userDao.queryUserById(id);
        logger.info("user:{}", user.toString());
        return user.getName();
    }
}
