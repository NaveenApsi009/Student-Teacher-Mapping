package com.example.Student.Teacher.Mapping.service;

import com.example.Student.Teacher.Mapping.dtos.StudentRequest;
import com.example.Student.Teacher.Mapping.dtos.StudentResponse;
import com.example.Student.Teacher.Mapping.dtos.TeacherResponse;
import com.example.Student.Teacher.Mapping.entity.Student;
import com.example.Student.Teacher.Mapping.entity.StudentTeacher;
import com.example.Student.Teacher.Mapping.entity.Teacher;
import com.example.Student.Teacher.Mapping.exceptions.ResourceNotFoundException;
import com.example.Student.Teacher.Mapping.repositories.StudentRepository;
import com.example.Student.Teacher.Mapping.repositories.StudentTeacherRepository;
import com.example.Student.Teacher.Mapping.repositories.TeacherRepository;
import jakarta.transaction.Transactional;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final StudentTeacherRepository studentTeacherRepository;

    public StudentServiceImpl(
            StudentRepository studentRepository,
            TeacherRepository teacherRepository,
            StudentTeacherRepository studentTeacherRepository
    ) {
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
        this.studentTeacherRepository = studentTeacherRepository;
    }

    @Transactional
    public StudentResponse createStudent(StudentRequest request) {
        Student student = new Student();
        student.setName(request.getName());
        student.setEmail(request.getEmail());

        Student savedStudent = studentRepository.save(student);



        return mapToStudentResponse(savedStudent);
    }

    public List<StudentResponse> getAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(this::mapToStudentResponse)
                .toList();
    }

    public StudentResponse getStudentById(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(()->new RuntimeException("Student is not found with"+id));
        return mapToStudentResponse(student);
    }

    public StudentResponse updateStudent(Long id, StudentRequest request) {
        Student student = studentRepository.findById(id).orElseThrow(()->new RuntimeException("Student is not found with"+id));

        student.setName(request.getName());
        student.setEmail(request.getEmail());

        Student updatedStudent = studentRepository.save(student);

        return mapToStudentResponse(updatedStudent);
    }

    @Transactional
    public void deleteStudent(Long id) {
        studentRepository.findById(id).orElseThrow(()->new RuntimeException("Student is not found with"+id));

        studentTeacherRepository.deleteByStudentId(id);
        studentRepository.deleteById(id);
    }

    public List<TeacherResponse> getTeachersByStudentId(Long studentId) {
        studentRepository.findById(studentId).orElseThrow(()->new RuntimeException("Student is not found with"+studentId));

        return studentTeacherRepository.findByStudentId(studentId)
                .stream()
                .map(mapping -> {
                    Teacher teacher = mapping.getTeacher();

                    return new TeacherResponse(
                            teacher.getId(),
                            teacher.getName(),
                            teacher.getSubject()
                    );
                })
                .toList();
    }

    @Transactional
    public String assignTeacherToStudent(Long studentId, Long teacherId) {
        Student student = studentRepository.findById(studentId).orElseThrow(()->
                new RuntimeException("Student not found with"+studentId));

        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new RuntimeException("Teacher not found with id: " + teacherId));

        boolean alreadyAssigned = studentTeacherRepository
                .findByStudentIdAndTeacherId(studentId, teacherId)
                .isPresent();

        if (alreadyAssigned) {
            return "Teacher already assigned to student";
        }

        StudentTeacher mapping = new StudentTeacher();
        mapping.setStudent(student);
        mapping.setTeacher(teacher);

        studentTeacherRepository.save(mapping);

        return "Teacher assigned successfully";
    }


    private StudentResponse mapToStudentResponse(Student student) {
        return new StudentResponse(
                student.getId(),
                student.getName(),
                student.getEmail()
        );
    }

}
