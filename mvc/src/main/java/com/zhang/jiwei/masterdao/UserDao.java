package com.zhang.jiwei.masterdao;

import com.zhang.jiwei.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author jiwei.zhang
 * @DATE 2017/12/18 0018
 */
@Repository
public interface UserDao {

    User queryUserById(@Param("id") int id);

    int insertUser(@Param("name") String name,@Param("age") int age);
}
