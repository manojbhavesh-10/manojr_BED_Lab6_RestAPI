package com.greatlearning.student.service;

import java.util.List;

import com.greatlearning.student.model.Student;

public interface StudentService {
	
	List<Student> getAllStudent();
	
	Student save(Student student);
	
	Student getStudentById(long id);
	
	Student updateTheStudent(Student employee);
	
	void deleteById(long id);
}
