package com.example.doubleselectionsystem.controller;

import com.example.doubleselectionsystem.component.EncryptComponent;
import com.example.doubleselectionsystem.component.MyToken;
import com.example.doubleselectionsystem.entity.Student;
import com.example.doubleselectionsystem.entity.User;
import com.example.doubleselectionsystem.service.StudentService;
import com.example.doubleselectionsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class LoginController {
    @Value("6983f953b49c88210cb9")
    private String roleTeacher;
    @Value("bb63e5f7e0f2ffae845c")
    private String roleStudent;
    @Value("ba66e5f7e0f2ccae835d")
    private String roleAdmin;
    @Value("7543f954c49c97510cb0")
    private String roleSelectedStudent;
    @Autowired
    private EncryptComponent encrypt;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private UserService userService;
    @Autowired
    private StudentService studentService;
    @PostMapping("login")
    public Map login(@RequestBody User login, HttpServletResponse response){
        User user=Optional.ofNullable(userService.getUser(login.getNumber()))
                .filter(u->encoder.matches(login.getPassword(),u.getPassword()))
                .orElseThrow(()->new ResponseStatusException(HttpStatus.UNAUTHORIZED,"您的用户名密码输入有误"));
        MyToken token=new MyToken(user.getId(),user.getRole());
        String auth=encrypt.encryptToken(token);
        response.setHeader("Access-Control-Expose-Headers",MyToken.AUTHORIZATION);
        response.setHeader(MyToken.AUTHORIZATION,auth);
        if(user.getRole() == User.Role.STUDENT){
            Student student=studentService.getStudent(user.getId());
            if(student.getTeacher()==null)
                return Map.of("role",roleStudent);
            else
                return Map.of("role",roleSelectedStudent,"selectedStudent",student);
        }
        else {
            if (user.getNumber() == 1001)
                return Map.of("role", roleAdmin);
            else
                return Map.of("role", roleTeacher);
        }
    }
}
