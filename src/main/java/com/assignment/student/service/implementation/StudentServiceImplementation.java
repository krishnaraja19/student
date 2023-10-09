package com.assignment.student.service.implementation;

import com.assignment.student.entity.Student;
import com.assignment.student.exception.ResourceNotFoundException;
import com.assignment.student.repository.StudentRepository;
import com.assignment.student.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImplementation implements StudentService {

    private static final Logger logger = LoggerFactory.getLogger(StudentServiceImplementation.class);
    @Autowired
    StudentRepository studentRepository;

    @Override
    public Student saveStudent(Student student) {
        logger.info("Saving student: {}", student);
        return studentRepository.save(student);
    }

    @Override
    public List<Student> getAllStudent() {
        logger.info("Finding all students");
        return studentRepository.findAll();
    }

    @Override
    public Student getStudentById(long id) {
        logger.info("Finding student by ID: {}", id);
        return studentRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Student does not exits with given id: " + id));
    }

    @Override
    public Student updateStudent(Long id,Student updatedStudent) {
        Student existingStudent = studentRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("Student does not exits with given id: "+id));

        logger.info("Updating student by ID: {}", id);
        existingStudent.setFirstName(updatedStudent.getFirstName());
        existingStudent.setLastName(updatedStudent.getLastName());
        existingStudent.setAge(updatedStudent.getAge());
        existingStudent.setSubjects(updatedStudent.getSubjects());

        return studentRepository.save(existingStudent);
    }

    @Override
    public void deleteStudent(long id) {
        Student student = studentRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Student does not exits with given id: " + id));
        if (student != null){
            logger.info("Deleting student with ID: {}", id);
            studentRepository.delete(student);
        }
    }
}
