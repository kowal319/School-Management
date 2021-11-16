package com.example.studentmanagement.repository;

import com.example.studentmanagement.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface StudentRepository extends JpaRepository<Student, Long > {


//    @Query(value = "select * from students where   first_name like %:keyword% or last_name like %:keyword%", nativeQuery = true)
//    List<Student> search(@Param("keyword") String keyword);
//
//    @Query(value = "select student.last_name from students  WHERE CONCAT(Student.firstName, ' ', Student.lastName, ' ', Student.classId) LIKE %?1%")
//    public List<Student> search(String keyword);


    @Query("SELECT students FROM Student students WHERE CONCAT(students.lastName, ' ', students.firstName) LIKE %?1%")
    public List<Student> search(String keyword);
}