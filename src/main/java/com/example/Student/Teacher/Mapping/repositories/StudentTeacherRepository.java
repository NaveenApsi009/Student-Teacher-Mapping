package com.example.Student.Teacher.Mapping.repositories;




import com.example.Student.Teacher.Mapping.entity.StudentTeacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentTeacherRepository extends JpaRepository<StudentTeacher, Long> {

    List<StudentTeacher> findByStudentId(Long studentId);

    Optional<StudentTeacher> findByStudentIdAndTeacherId(Long studentId, Long teacherId);

    void deleteByStudentId(Long studentId);
}
