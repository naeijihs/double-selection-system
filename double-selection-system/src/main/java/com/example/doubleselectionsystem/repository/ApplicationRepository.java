package com.example.doubleselectionsystem.repository;

import com.example.doubleselectionsystem.entity.Application;
import com.example.doubleselectionsystem.entity.Student;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepository extends BaseRepository<Application,Integer>{
    @Query(value = "select * from application a where a.teacher_user_id=:tid and a.state='0'",nativeQuery = true)
    List<Application> findByT(@Param("tid")int tid);
    @Query("from Application a where a.teacher.id=:tid and a.student.id=:sid")
    Application findByTS(@Param("tid") int tid,@Param("sid") int sid);
    @Modifying
    @Query("delete from Application a where a.student=:student")
    void deleteByStudent(@Param("student")Student student);
}
