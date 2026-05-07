package com.example.Student.Teacher.Mapping.service;

import com.example.Student.Teacher.Mapping.dtos.StudentRequest;
import com.example.Student.Teacher.Mapping.dtos.StudentResponse;
import com.example.Student.Teacher.Mapping.dtos.TeacherResponse;

import java.util.List;

public interface StudentService {

    StudentResponse createStudent(StudentRequest request);

    List<StudentResponse> getAllStudents();

    StudentResponse getStudentById(Long id);

    StudentResponse updateStudent(Long id, StudentRequest request);

    void deleteStudent(Long id);

    List<TeacherResponse> getTeachersByStudentId(Long studentId);

    String assignTeacherToStudent(Long studentId, Long teacherId);
}
