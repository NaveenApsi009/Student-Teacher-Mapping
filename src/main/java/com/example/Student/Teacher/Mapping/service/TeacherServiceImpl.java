package com.example.Student.Teacher.Mapping.service;

import com.example.Student.Teacher.Mapping.dtos.TeacherResponse;
import com.example.Student.Teacher.Mapping.entity.Teacher;
import com.example.Student.Teacher.Mapping.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TeacherServiceImpl implements TeacherService{

    TeacherRepository teacherRepository;


    @Autowired
    public TeacherServiceImpl(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public TeacherResponse createTeacher(Teacher teacher) {

         return mapTeacherToTeacherResponse( teacherRepository.save(teacher));

    }

    @Override
    public List<TeacherResponse> getAllTeacher() {
        List<Teacher> teachers=teacherRepository.findAll();
        return mapListOfTeacherToTeacherResponse(teachers);


    }

    public List<TeacherResponse> mapListOfTeacherToTeacherResponse(List<Teacher> listOfTeacher){

       return listOfTeacher.stream().map(teacher -> {
            TeacherResponse teacherResponse = new TeacherResponse();
            teacherResponse.setId(teacher.getId());
            teacherResponse.setName(teacher.getName());
            teacherResponse.setSubject(teacher.getSubject());
             return teacherResponse;})
               .toList();

    }

    public TeacherResponse mapTeacherToTeacherResponse(Teacher teacher){


                    TeacherResponse teacherResponse = new TeacherResponse();
                    teacherResponse.setId(teacher.getId());
                    teacherResponse.setName(teacher.getName());
                    teacherResponse.setSubject(teacher.getSubject());
                    return teacherResponse;

    }
}
