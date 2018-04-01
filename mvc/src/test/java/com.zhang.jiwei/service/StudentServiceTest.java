package java.com.zhang.jiwei.service;

import com.alibaba.fastjson.JSONObject;
import com.zhang.jiwei.config.SpringConfig;
import com.zhang.jiwei.entity.Student;
import com.zhang.jiwei.service.StudentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author jiwei.zhang
 * @DATE 2018-03-30 下午 15:55
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringConfig.class})
public class StudentServiceTest {

    @Autowired
    private StudentService studentService;

    @Test
    public void getStudentByIdTest(){
        Student student = studentService.getStudentById(1);
        System.out.println(JSONObject.toJSONString(student));
        System.out.println(student.toString());
    }
}
