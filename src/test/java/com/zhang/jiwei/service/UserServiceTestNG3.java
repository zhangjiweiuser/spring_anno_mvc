package com.zhang.jiwei.service;

import com.zhang.jiwei.config.MvcConfig;
import com.zhang.jiwei.config.SpringConfig;
import com.zhang.jiwei.dao.UserDao;
import com.zhang.jiwei.entity.User;
import com.zhang.jiwei.service.impl.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;

/**
 * @author jiwei.zhang
 * @DATE 2017/12/18 0018
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {SpringConfig.class, MvcConfig.class})
public class UserServiceTestNG3 extends AbstractTestNGSpringContextTests {

    @Mock
    private UserDao userDao;

    @InjectMocks
    private UserService userService = new UserServiceImpl();

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void userServiceTest() {
        when(userDao.queryUserById(anyInt())).thenReturn(new User(10,"hello",16));
        String name = userService.getUserNameById(1);
        System.out.println(name);
    }


}
