package com.zhang.jiwei.service.impl;

import com.zhang.jiwei.dao.RoleDao;
import com.zhang.jiwei.dao.UserDao;
import com.zhang.jiwei.datasource.DataSourceManager;
import com.zhang.jiwei.datasource.DataSources;
import com.zhang.jiwei.entity.Role;
import com.zhang.jiwei.entity.User;
import com.zhang.jiwei.service.UserService;
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
    @Transactional(rollbackFor = {Exception.class})
    public int update(String name) {
        DataSourceManager.set(DataSources.MASTER);
        userDao.insertUser(name, (int) (Math.random()*30));

        if(name.equals("san")){
            throw new RuntimeException();
        }
        DataSourceManager.set(DataSources.SLAVE);
        Role role = new Role();
        role.setRoleName("jiaose");
        int num = roleDao.insertRole(role);
        return num;
    }
}
