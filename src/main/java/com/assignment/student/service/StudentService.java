package com.assignment.student.service;

import com.assignment.student.entity.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    Student saveStudent(Student student);
    List<Student> getAllStudent();
    Student getStudentById(long id);
    Student updateStudent(Long id,Student updatedStudent);
    void deleteStudent(long id);
}
