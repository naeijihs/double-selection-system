package com.example.doubleselectionsystem.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Application {
    public enum State{
        UNSETTLED,REFUSED
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private Student student;
    @ManyToOne
    private Teacher teacher;
    private State state;
    private String selfIntroduction;
}
