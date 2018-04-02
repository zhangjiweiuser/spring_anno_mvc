package com.zhang.jiwei.service;

import com.zhang.jiwei.config.SpringConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by zhangjiwei on 2018/4/2.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringConfig.class})
public class MultiDataSourceTest {

    @Autowired
    private UserService userService;

    @Test
    public void insertTest() {
        int num = userService.update("san");
        Assert.assertTrue(num == 1);
    }
}
