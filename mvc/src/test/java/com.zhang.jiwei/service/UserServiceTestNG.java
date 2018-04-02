package com.zhang.jiwei.service;

import com.zhang.jiwei.config.MvcConfig;
import com.zhang.jiwei.config.SpringConfig;
import com.zhang.jiwei.controller.UserController;
import com.zhang.jiwei.masterdao.UserDao;
import com.zhang.jiwei.entity.User;
import com.zhang.jiwei.service.UserService;
import com.zhang.jiwei.service.impl.UserServiceImpl;
import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.testng.annotations.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author jiwei.zhang
 * @DATE 2017/12/18 0018
 */
@EnableWebMvc
@WebAppConfiguration
@ContextConfiguration(classes = {SpringConfig.class, MvcConfig.class})
public class UserServiceTestNG extends AbstractTestNGSpringContextTests {

    private MockMvc mockMvc;

    @Mock
    private UserDao userDao;

    @InjectMocks
    @Autowired
    private UserService userService = new UserServiceImpl();

    @InjectMocks
    private UserController userController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void userServiceTest() {
        when(userDao.queryUserById(anyInt())).thenReturn(new User(1, "zhangsan", 15));
        String name = userService.getUserNameById(15);
        assertEquals("zhangsan", name);
    }

    @Test
    public void userControllerTest() throws Exception {
        when(userDao.queryUserById(anyInt())).thenReturn(new User(1, "zhangsan", 15));
        mockMvc.perform(get("/getUserNameById").param("id", "10"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(result -> assertEquals("zhangsan", result.getResponse().getContentAsString()));
    }
}
