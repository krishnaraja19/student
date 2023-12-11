package com.assignment.student.controller;

import com.assignment.student.entity.Student;
import com.assignment.student.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/api/v0/students")
public class StudentController {

    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

    private final StudentService studentService;
    @Autowired
    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    @Operation(summary = "Create a new student")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Student created",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Student.class)) })
    })
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Student> createStudent(@Valid @RequestBody Student student){

        logger.info("Create student request initiated..");
        //Creating student here
        Student createdStudent =  studentService.saveStudent(student);

        // Return the created Student with a 201 Created status
        return ResponseEntity.status(HttpStatus.CREATED).body(createdStudent);
    }

    @Operation(summary = "Get a list of all students")
    @GetMapping("/")
    public ResponseEntity<List<Student>> getAllStudents() {
        logger.info("Get all student request initiated..");

        List<Student> students = studentService.getAllStudent();

        if (students.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 No Content if no students are found
        }
        return ResponseEntity.ok(students); // 200 OK with a list of students
    }

    @Operation(summary = "Get a student by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Student.class)) }),
            @ApiResponse(responseCode = "404", description = "Student not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        logger.info("Get student by id request initiated..");

        Student student = studentService.getStudentById(id);

        return ResponseEntity.ok(student);
    }

    @Operation(summary = "Update a student by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student updated",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Student.class)) }),
            @ApiResponse(responseCode = "404", description = "Student not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id,@Valid @RequestBody Student student) {
        logger.info("Update student request initiated..");

        Student updatedStudent = studentService.updateStudent(id, student);
        return ResponseEntity.ok(updatedStudent);
    }

    @Operation(summary = "Delete a student by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Student deleted"),
            @ApiResponse(responseCode = "404", description = "Student not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {

        logger.info("Delete student request initiated..");

        studentService.deleteStudent(id);
        logger.info("Student with {} deleted successfully",id);

        return ResponseEntity.ok("Student with ID "+id+" deleted successfully.");

    }
}
