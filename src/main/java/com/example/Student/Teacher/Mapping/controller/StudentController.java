package com.example.Student.Teacher.Mapping.controller;

import com.example.Student.Teacher.Mapping.dtos.StudentRequest;
import com.example.Student.Teacher.Mapping.dtos.StudentResponse;
import com.example.Student.Teacher.Mapping.dtos.TeacherResponse;
import com.example.Student.Teacher.Mapping.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<StudentResponse> createStudent(@RequestBody StudentRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.createStudent(request));
    }

    @GetMapping
    public ResponseEntity<List<StudentResponse>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponse> getStudentById(@PathVariable Long id) {

        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentResponse> updateStudent(
            @PathVariable Long id,
            @RequestBody StudentRequest request
    ) {
        return ResponseEntity.ok(studentService.updateStudent(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{studentId}/teachers")
    public ResponseEntity<List<TeacherResponse>> getTeachersByStudentId(
            @PathVariable Long studentId
    ) {
        return ResponseEntity.ok(studentService.getTeachersByStudentId(studentId));
    }

    @PostMapping("/{studentId}/teachers/{teacherId}")
    public ResponseEntity<String> assignTeacherToStudent(
            @PathVariable Long studentId,
            @PathVariable Long teacherId
    ) {
        return ResponseEntity.ok(studentService.assignTeacherToStudent(studentId, teacherId));
    }
}
