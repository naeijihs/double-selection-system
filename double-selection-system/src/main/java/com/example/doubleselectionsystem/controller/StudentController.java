package com.example.doubleselectionsystem.controller;

import com.example.doubleselectionsystem.entity.Application;
import com.example.doubleselectionsystem.service.StudentService;
import com.example.doubleselectionsystem.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/student/")
public class StudentController {
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private StudentService studentService;
    @GetMapping("index")
    public Map getTeachers(){
        return Map.of("teachers",teacherService.getTeachers());
    }
    @PostMapping("submitApplication")
    public Map submitApplication(@RequestBody Application application){
        return Map.of("application",studentService.addApplication(application));
    }
    @GetMapping("checkApplication/{tid}")
    public Map checkApplication(@PathVariable int tid){
        if(studentService.getApplication(tid)==null)
            return Map.of("application","");
        else
            return Map.of("application",studentService.getApplication(tid));    //通过老师和学生id查看唯一申请单
    }
}
