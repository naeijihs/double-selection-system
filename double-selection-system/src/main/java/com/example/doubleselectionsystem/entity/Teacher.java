package com.example.doubleselectionsystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@JsonIgnoreProperties({"selectedStudents","applications"})
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne(cascade = CascadeType.PERSIST)
    @MapsId
    private User user;
    private int currentNum;
    private int maxNum;
    @OneToMany(mappedBy = "teacher")
    private List<Student> selectedStudents;
    @OneToMany(mappedBy = "teacher")
    private List<Application> applications;
}
