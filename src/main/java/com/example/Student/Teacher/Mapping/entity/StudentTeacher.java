package com.example.Student.Teacher.Mapping.entity;



import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "student_teacher")
@Data
public class StudentTeacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "teacher_id", nullable = false)
    private Teacher teacher;


}
