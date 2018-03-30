package com.zhang.jiwei.service;

import com.zhang.jiwei.config.SpringConfig;
import com.zhang.jiwei.dao.UserDao;
import com.zhang.jiwei.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.Test;

/**
 * @author jiwei.zhang
 * @DATE 2017/12/18 0018
 */
@WebAppConfiguration
@ContextConfiguration(classes = {SpringConfig.class})
public class UserServiceTestNG2 extends AbstractTestNGSpringContextTests {

    @Autowired
    private UserDao userDao;

    @Test
    public void userServiceTest() {
        User user = userDao.queryUserById(1);
        System.out.println(user.toString());
    }

}
