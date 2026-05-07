package com.example.Student.Teacher.Mapping.service;

import com.example.Student.Teacher.Mapping.dtos.TeacherResponse;
import com.example.Student.Teacher.Mapping.entity.Teacher;

import java.util.List;

public interface TeacherService {

    public TeacherResponse createTeacher(Teacher teacher);
    public List<TeacherResponse> getAllTeacher();

}
