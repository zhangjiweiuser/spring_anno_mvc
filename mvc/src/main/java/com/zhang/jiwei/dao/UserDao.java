package com.zhang.jiwei.dao;

import com.zhang.jiwei.entity.User;
import org.apache.ibatis.annotations.Param;

/**
 * @author jiwei.zhang
 * @DATE 2017/12/18 0018
 */
public interface UserDao {

    User queryUserById(@Param("id") int id);

    int insertUser(@Param("name") String name,@Param("age") int age);
}
