package com.example.Student.Teacher.Mapping.repositories;



import com.example.Student.Teacher.Mapping.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
