package com.example.doubleselectionsystem.controller;

import com.example.doubleselectionsystem.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/teacher/")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;
    @GetMapping("index")
    public Map getSelectedStudentsAndUnsettledApplications(){
        return Map.of("selectedStudents",teacherService.getSelectedStudents(),
                "unsettledApplications",teacherService.getUnsettledApplications());
    }
    @GetMapping("agree/{aid}")
    public void agreeApplication(@PathVariable int aid){
        teacherService.agreeApplication(aid);
    }
    @GetMapping("refuse/{aid}")
    public void refuseApplication(@PathVariable int aid){
        teacherService.refuseApplication(aid);
    }
}
