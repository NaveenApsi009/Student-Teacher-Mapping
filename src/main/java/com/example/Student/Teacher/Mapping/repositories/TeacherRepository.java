package com.example.Student.Teacher.Mapping.repositories;




import com.example.Student.Teacher.Mapping.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}