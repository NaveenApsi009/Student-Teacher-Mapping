package com.example.Student.Teacher.Mapping.controller;


import com.example.Student.Teacher.Mapping.dtos.TeacherResponse;
import com.example.Student.Teacher.Mapping.entity.Teacher;
import com.example.Student.Teacher.Mapping.service.StudentServiceImpl;
import com.example.Student.Teacher.Mapping.service.TeacherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TeacherController {

    private TeacherServiceImpl teacherServiceImpl;
    @Autowired
    public TeacherController(TeacherServiceImpl teacherServiceImpl) {
        this.teacherServiceImpl = teacherServiceImpl;
    }

    @PostMapping("/create/teacher")
    public ResponseEntity<TeacherResponse> createTeacher(@RequestBody Teacher teacher){

        return new ResponseEntity<>(teacherServiceImpl.createTeacher(teacher), HttpStatus.CREATED);
    }

    @GetMapping("/get/teacher")
    public ResponseEntity<List<TeacherResponse>> getAllTeacher(){
        return new ResponseEntity<>(teacherServiceImpl.getAllTeacher(),HttpStatus.OK);
    }

}
