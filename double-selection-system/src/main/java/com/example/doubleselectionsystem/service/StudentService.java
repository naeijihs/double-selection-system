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
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class StudentService {
    @Autowired
    private ApplicationRepository applicationRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private RequestComponent requestComponent;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder encoder;
    public List<Student> addStudents(List<Student> students){
        List<Student> ss=students.stream().filter(student -> {
            if(userRepository.find(student.getUser().getNumber())==null){
                return true;
            }
            else
                return false;
        }).collect(Collectors.toList());
        ss.forEach(student -> {
            student.getUser().setRole(User.Role.STUDENT);
            student.getUser().setPassword(encoder.encode(String.valueOf(student.getUser().getNumber())));
        });
        return studentRepository.saveAll(ss);}
    public Student getStudent(int uid){
        return studentRepository.findById(uid).orElse(null);
    }
    public List<Student> getStudents(){
        return studentRepository.findAll();
    }
    public Application addApplication(Application application){
        Student student=studentRepository.findById(requestComponent.getUid()).orElse(null);
        Teacher teacher=teacherRepository.findById(application.getTeacher().getId()).orElse(null);
        application.setStudent(student);
        application.setTeacher(teacher);
        application.setState(Application.State.UNSETTLED);
        return applicationRepository.save(application);
    }
    public Application getApplication(int tid){
        return applicationRepository.findByTS(tid,requestComponent.getUid());   //通过老师和学生id查看唯一申请单
    }
}
