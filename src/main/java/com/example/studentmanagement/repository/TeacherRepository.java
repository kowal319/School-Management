package com.example.studentmanagement.repository;

import com.example.studentmanagement.entity.Student;
import com.example.studentmanagement.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    @Query("SELECT teachers FROM Teacher teachers WHERE CONCAT(teachers.lastName, ' ', teachers.firstName) LIKE %?1%")
    public List<Teacher> search(String keyword);
}

