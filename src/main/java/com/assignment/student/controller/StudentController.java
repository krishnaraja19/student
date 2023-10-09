package com.assignment.student.controller;

import com.assignment.student.entity.Student;
import com.assignment.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/api/v0/students")
public class StudentController {

    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    StudentService studentService;

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public Student createStudent(@RequestBody Student student){
        logger.info("Create student request initiated..");
        return studentService.saveStudent(student);
    }

    @GetMapping("/")
    public List<Student> getAllStudents() {
        logger.info("Get all student request initiated..");
        return studentService.getAllStudent();
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Long id) {
        logger.info("Get student by id request initiated..");
        return studentService.getStudentById(id);
    }

    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable Long id,@RequestBody Student updateStudent) {
        logger.info("Update student request initiated..");
        return studentService.updateStudent(id,updateStudent);
    }

    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable Long id) {

        logger.info("Delete student request initiated..");

        studentService.deleteStudent(id);
        logger.info("Student with {} deleted successfully",id);

        return "Student with ID "+id+" deleted successfully.";

    }
}
