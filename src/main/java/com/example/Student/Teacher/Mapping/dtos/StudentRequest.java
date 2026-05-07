package com.example.Student.Teacher.Mapping.dtos;



import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class StudentRequest {

    private String name;
    private String email;
    private List<Long> teacherIds;


}