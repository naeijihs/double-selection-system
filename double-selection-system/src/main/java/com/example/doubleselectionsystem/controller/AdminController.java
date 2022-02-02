package com.example.doubleselectionsystem.controller;

import com.example.doubleselectionsystem.entity.Student;
import com.example.doubleselectionsystem.entity.Teacher;
import com.example.doubleselectionsystem.service.StudentService;
import com.example.doubleselectionsystem.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/")
public class AdminController {
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private StudentService studentService;
    @RequestMapping("index")
    public Map getStudentsAndTeachers(){
        return Map.of("students",studentService.getStudents(),"teachers",teacherService.getTeachers());
    }
    @RequestMapping("addTeachers")
    public Map addTeachers(@RequestBody List<Teacher> teachers){
        return Map.of("teachers",teacherService.addTeachers(teachers));
    }
    @RequestMapping("addStudents")
    public Map addStudents(@RequestBody List<Student> students){
        return Map.of("students",studentService.addStudents(students));
    }
}
