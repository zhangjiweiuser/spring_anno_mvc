package com.zhang.jiwei.service;

import com.zhang.jiwei.config.MvcConfig;
import com.zhang.jiwei.config.SpringConfig;
import com.zhang.jiwei.dao.UserDao;
import com.zhang.jiwei.entity.User;
import com.zhang.jiwei.service.impl.UserServiceImpl;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.testng.AssertJUnit.assertEquals;

/**
 * @author jiwei.zhang
 * @DATE 2017/12/18 0018
 */

@WebAppConfiguration
@ContextConfiguration(classes = {SpringConfig.class, MvcConfig.class})
@TestExecutionListeners(listeners = DependencyInjectionTestExecutionListener.class)
public class UserServiceTestNG5 extends AbstractTestNGSpringContextTests {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Mock
    private UserDao userDao;

    @InjectMocks
    @Autowired
    private UserService userService = new UserServiceImpl();

    @BeforeClass
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }


    @Test
    public void userControllerTest() throws Exception {
        when(userDao.queryUserById(anyInt())).thenReturn(new User(10, "hello", 16));
        mockMvc.perform(get("/getUserNameById").param("id", "5")).andDo(print())
                .andExpect(mvcResult -> assertEquals("hello", mvcResult.getResponse().getContentAsString()));
    }

}
