package com.zhang.jiwei.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.zhang.jiwei.config.SpringConfig;
import com.zhang.jiwei.dao.UserDao;
import com.zhang.jiwei.entity.User;
import com.zhang.jiwei.service.impl.UserServiceImpl;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;
import static org.testng.AssertJUnit.assertEquals;

/**
 * @author jiwei.zhang
 * @DATE 2017/12/18 0018
 */
@WebAppConfiguration
@ContextConfiguration(classes = {SpringConfig.class})
@TestExecutionListeners(listeners = DependencyInjectionTestExecutionListener.class)
public class UserServiceTestNG4 extends AbstractTestNGSpringContextTests {

    @Mock
    private UserDao userDao;

    @InjectMocks
    private UserService userService = new UserServiceImpl();

    @BeforeTest
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void userServiceTest() {
        when(userDao.queryUserById(anyInt())).thenReturn(new User(10, "hello", 16));
        String name = userService.getUserNameById(1);
        System.out.println(name);
    }

    @Test(dataProvider = "user")
    public void userServiceWithDataProviderTest(int id, String expectText) {
        when(userDao.queryUserById(anyInt())).thenReturn(new User(10, "hello", 16));
        String name = userService.getUserNameById(id);
        assertEquals(expectText, name);
    }


    @DataProvider
    public Object[][] user() {
        System.out.println();
        String str = null;
        List<Object[]> objList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(UserServiceTestNG4.class.getClassLoader().getResourceAsStream("User.txt")))) {
            while ((str = br.readLine()) != null) {
                String[] strArr = str.split(",");
                Object[] obj = new Object[]{Integer.valueOf(strArr[0]), strArr[1]};
                objList.add(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Object[][] objTwo = new Object[objList.size()][];
        for(int i=0;i<objList.size();i++){
            objTwo[i] = objList.get(i);
        }
        return objTwo;
//        return new Object[][]{
//                {1, "hello"},
//                {2, "hello"},
//                {3, "hello2"},
//                {4, "hello"},
//                {5, "hello"}
//        };
    }

}
