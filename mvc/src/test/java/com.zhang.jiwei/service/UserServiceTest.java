package com.zhang.jiwei.service;

import com.alibaba.fastjson.JSONObject;
import com.zhang.jiwei.config.MvcConfig;
import com.zhang.jiwei.config.SpringConfig;
import com.zhang.jiwei.controller.UserController;
import com.zhang.jiwei.masterdao.UserDao;
import com.zhang.jiwei.entity.User;
import com.zhang.jiwei.service.UserService;
import com.zhang.jiwei.service.impl.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.testng.AssertJUnit;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author jiwei.zhang
 * @DATE 2017/12/18 0018
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {SpringConfig.class, MvcConfig.class})
public class UserServiceTest {

    private MockMvc mockMvc;

    @Mock
    private UserDao userDao;

    @InjectMocks
    @Autowired
    private UserService userService = new UserServiceImpl();

    @Autowired
    private UserController userController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(userController)
                .addFilters(new CharacterEncodingFilter("UTF-8", true))
                .build();
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

    @Test
    public void userControllerTest1() throws Exception {
        when(userDao.queryUserById(anyInt())).thenReturn(new User(10, "hello", 16));
        mockMvc.perform(get("/updateUser").param("id", "5").param("name", "lisi").param("age", "18"))
                .andDo(print())
                .andExpect(mvcResult -> AssertJUnit.assertEquals("lisi", mvcResult.getResponse().getContentAsString()));
    }

    @Test
    public void userControllerTest2() throws Exception {
        when(userDao.queryUserById(anyInt())).thenReturn(new User(10, "hello", 16));
        mockMvc.perform(get("/getUserById").param("id", "3"))
                .andDo(print())
                .andExpect(mvcResult -> AssertJUnit.assertEquals("hello", JSONObject.parseObject(mvcResult.getResponse().getContentAsString()).getString("name")));
    }

    @Test
    public void userControllerTest3() throws Exception {
        mockMvc.perform(get("/getUserByName").param("name", "张三"))
                .andDo(print())
                .andExpect(mvcResult -> AssertJUnit.assertEquals("张三", JSONObject.parseObject(mvcResult.getResponse().getContentAsString()).getString("name")));
    }

    @Test
    public void userControllerUpdateTest() throws Exception {
        when(userDao.queryUserById(anyInt())).thenReturn(new User(10, "hello", 16));
//        MultiValueMap<String,String> param = CollectionUtils.toMultiValueMap(new HashMap<>());
        MultiValueMap<String,String> param = new LinkedMultiValueMap<>();
        param.add("id","5");
        param.add("name","lisi");
        param.add("age","18");
        mockMvc.perform(post("/updateUser")
                .accept(MediaType.APPLICATION_JSON)
                .params(param))
                .andDo(print())
                .andExpect(mvcResult -> AssertJUnit.assertEquals("lisi", JSONObject.parseObject(mvcResult.getResponse().getContentAsString()).getString("name")));
    }
}
