package com.example.doubleselectionsystem.controller;

import com.example.doubleselectionsystem.entity.User;
import com.example.doubleselectionsystem.service.TeacherService;
import com.example.doubleselectionsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/common/")
public class CommonController {
    @Autowired
    private UserService userService;
    @PostMapping("modifyPassword")
    public Map modifyPassword(@RequestBody User user){
        userService.modifyPassword(user.getPassword());
        return Map.of("data","success");
    }

}
