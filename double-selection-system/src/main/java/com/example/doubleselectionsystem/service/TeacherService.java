package com.example.doubleselectionsystem.service;

import com.example.doubleselectionsystem.component.RequestComponent;
import com.example.doubleselectionsystem.entity.Application;
import com.example.doubleselectionsystem.entity.Student;
import com.example.doubleselectionsystem.entity.Teacher;
import com.example.doubleselectionsystem.entity.User;
import com.example.doubleselectionsystem.repository.ApplicationRepository;
import com.example.doubleselectionsystem.repository.StudentRepository;
import com.example.doubleselectionsystem.repository.TeacherRepository;
import com.example.doubleselectionsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private ApplicationRepository applicationRepository;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private RequestComponent requestComponent;
    public Teacher addTeacher(Teacher teacher){
        return teacherRepository.save(teacher);
    }
    public List<Teacher> addTeachers(List<Teacher> teachers){
        List<Teacher> ts=teachers.stream().filter(teacher -> {
            if(userRepository.find(teacher.getUser().getNumber())==null){
                return true;
            }
            else
                return false;
        }).collect(Collectors.toList());
        ts.forEach(teacher -> {
            teacher.getUser().setRole(User.Role.TEACHER);
            teacher.getUser().setPassword(encoder.encode(String.valueOf(teacher.getUser().getNumber())));
            teacher.setMaxNum(10);
        });
        return teacherRepository.saveAll(ts);
    }
    public List<Teacher> getTeachers(){ return teacherRepository.findAll();}
    public List<Student> getSelectedStudents(){
        Teacher teacher=teacherRepository.findById(requestComponent.getUid()).orElse(null);
        return teacher.getSelectedStudents();
    }
    public List<Application> getUnsettledApplications(){
        return applicationRepository.findByT(requestComponent.getUid());
    }
    public void agreeApplication(int aid){
        Application application=applicationRepository.findById(aid).orElse(null);
        if (application!=null){
            Student student=application.getStudent();
            Teacher teacher=application.getTeacher();
            if(student.getTeacher()==null){
                int cur=teacher.getCurrentNum();
                teacher.setCurrentNum(++cur);
                teacherRepository.save(teacher);
                student.setTeacher(teacher);
                studentRepository.save(student);
                applicationRepository.deleteByStudent(student);     //双选后删除该学生的所有申请
            }
        }
    }
    public void refuseApplication(int aid){
        Application application=applicationRepository.findById(aid).orElse(null);
        application.setState(Application.State.REFUSED);
        applicationRepository.save(application);
    }
}
