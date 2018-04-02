package com.zhang.jiwei.service.impl;

import com.zhang.jiwei.entity.Role;
import com.zhang.jiwei.entity.User;
import com.zhang.jiwei.masterdao.UserDao;
import com.zhang.jiwei.service.UserService;
import com.zhang.jiwei.slavedao.RoleDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author jiwei.zhang
 * @DATE 2017/12/18 0018
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    public String getUserNameById(int id) {
        User user = userDao.queryUserById(id);
        logger.info("user:{}", user.toString());
        return user.getName();
    }

    @Override
    public User getUserById(int id) {
        return userDao.queryUserById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class,transactionManager = "transactionManager")
    public int update(String name) {
        userDao.insertUser(name, (int) (Math.random() * 30));

        if (name.equals("san")) {
            throw new RuntimeException();
        }
        Role role = new Role();
        role.setRoleName("jiaose");
        int num = roleDao.insertRole(role);
        return num;
    }
}
