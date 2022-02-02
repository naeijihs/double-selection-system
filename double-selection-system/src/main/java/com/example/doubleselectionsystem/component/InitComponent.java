package com.example.doubleselectionsystem.component;

import com.example.doubleselectionsystem.entity.Teacher;
import com.example.doubleselectionsystem.entity.User;
import com.example.doubleselectionsystem.service.TeacherService;
import com.example.doubleselectionsystem.service.UserService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class InitComponent implements InitializingBean {
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private UserService userService;
    @Autowired
    private TeacherService teacherService;
    @Override
    public void afterPropertiesSet() throws Exception {
        int num=1001;
        User user=userService.getUser(num);
        if (user==null){
            User u=new User();
            u.setName("管理员");
            u.setNumber(num);
            u.setRole(User.Role.TEACHER);
            u.setPassword(encoder.encode(String.valueOf(num)));
            Teacher teacher=new Teacher();
            teacher.setUser(u);
            teacherService.addTeacher(teacher);
        }
    }
}
