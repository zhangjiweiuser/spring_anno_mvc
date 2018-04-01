package com.zhang.jiwei.service.impl;

import com.zhang.jiwei.dao.StudentDao;
import com.zhang.jiwei.entity.Student;
import com.zhang.jiwei.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author jiwei.zhang
 * @DATE 2018-03-30 下午 15:58
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDao studentDao;

    @Override
    public Student getStudentById(int id) {
        return studentDao.getStudentById(id);
    }
}
