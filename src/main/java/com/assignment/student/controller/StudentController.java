package com.assignment.student.controller;

import com.assignment.student.entity.Student;
import com.assignment.student.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Create a new student")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Student created",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Student.class)) })
    })
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public Student createStudent(@RequestBody Student student){
        logger.info("Create student request initiated..");
        return studentService.saveStudent(student);
    }

    @Operation(summary = "Get a list of all students")
    @GetMapping("/")
    public List<Student> getAllStudents() {
        logger.info("Get all student request initiated..");
        return studentService.getAllStudent();
    }

    @Operation(summary = "Get a student by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Student.class)) }),
            @ApiResponse(responseCode = "404", description = "Student not found")
    })
    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Long id) {
        logger.info("Get student by id request initiated..");
        return studentService.getStudentById(id);
    }

    @Operation(summary = "Update a student by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student updated",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Student.class)) }),
            @ApiResponse(responseCode = "404", description = "Student not found")
    })
    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable Long id,@RequestBody Student updateStudent) {
        logger.info("Update student request initiated..");
        return studentService.updateStudent(id,updateStudent);
    }

    @Operation(summary = "Delete a student by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Student deleted"),
            @ApiResponse(responseCode = "404", description = "Student not found")
    })
    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable Long id) {

        logger.info("Delete student request initiated..");

        studentService.deleteStudent(id);
        logger.info("Student with {} deleted successfully",id);

        return "Student with ID "+id+" deleted successfully.";

    }
}
