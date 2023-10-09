package com.assignment.student;

import com.assignment.student.controller.StudentController;
import com.assignment.student.entity.Student;
import com.assignment.student.repository.StudentRepository;
import com.assignment.student.service.StudentService;
import com.assignment.student.util.JsonUtil;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
@SpringJUnitConfig
class StudentApplicationTests {

	@InjectMocks
	private StudentController studentController;

	@Mock
	private StudentService studentService;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	StudentRepository studentRepository;

	private final String url="/api/v0/students";

	@Test
	public void testCreateStudent() throws Exception {
		Student student = Student.builder().firstName("Adam").lastName("Smith").age(34).build();

		Mockito.when(studentService.saveStudent(student)).thenReturn(student);

		mockMvc.perform(MockMvcRequestBuilders.post(url+"/")
						.contentType(MediaType.APPLICATION_JSON)
						.content(JsonUtil.toJson(student)))
				.andExpect(MockMvcResultMatchers.status().isCreated())
				.andReturn();

	}

	@Test
	public void testGetAllStudents() throws Exception {
		List<Student> students = new ArrayList<>();
		students.add(Student.builder().firstName("Adam").lastName("Smith").age(34).build());
		students.add(Student.builder().firstName("Sekar").lastName("Matheshwaran").age(23).build());

		Mockito.when(studentService.getAllStudent()).thenReturn(students);

		mockMvc.perform(MockMvcRequestBuilders.get(url+"/"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
	}

	@Test
	@Sql(statements = "INSERT INTO STUDENT (id,first_name,last_name,age) VALUES(123,'Steve','Roger',33)",
			executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM STUDENT WHERE id=123",
			executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
	public void testGetStudentById() throws Exception {
		Long studentId = 123L;
		Student student = Student.builder().id(studentId).firstName("Steve").lastName("Roger").age(33).build();

		Mockito.when(studentService.getStudentById(studentId)).thenReturn(student);

		mockMvc.perform(MockMvcRequestBuilders.get(url+"/{id}", studentId))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
	}

	@Test
	@Sql(statements = "INSERT INTO STUDENT (id,first_name,last_name,age) VALUES(124,'Poul','Anderson',45)",
		executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM STUDENT WHERE id=124",
			executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
	public void testUpdateStudent() throws Exception {
		Long studentId = 124L;
		Student student = Student.builder().id(studentId).firstName("Katherine").lastName("Anderson").age(34).build();

		Mockito.when(studentService.updateStudent(studentId,student)).thenReturn(student);

		mockMvc.perform(MockMvcRequestBuilders.put(url+"/{id}", studentId)
						.contentType(MediaType.APPLICATION_JSON)
						.content(JsonUtil.toJson(student)))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	@Sql(statements = "INSERT INTO STUDENT (id,first_name,last_name,age) VALUES(125,'Ulrika','Johnson',27)",
			executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	public void testDeleteStudent() throws Exception {
		Long studentId = 125L;

		mockMvc.perform(MockMvcRequestBuilders.delete(url+"/{id}", studentId))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

}
