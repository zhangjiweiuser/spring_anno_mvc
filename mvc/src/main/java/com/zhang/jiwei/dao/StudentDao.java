package com.zhang.jiwei.dao;

import com.zhang.jiwei.entity.Student;
import org.apache.ibatis.annotations.Param;

/**
 * @author jiwei.zhang
 * @DATE 2018-03-30 下午 15:18
 */
public interface  StudentDao {

    Student getStudentById(@Param("id") int id);
}
