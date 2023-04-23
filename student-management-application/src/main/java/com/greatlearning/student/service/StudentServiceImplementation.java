package com.greatlearning.student.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greatlearning.student.model.Student;
import com.greatlearning.student.repository.StudentRepository;

@Service
public class StudentServiceImplementation implements StudentService {

	@Autowired
	private StudentRepository studentRepository;

	@Override
	public List<Student> getAllStudent() {
		return studentRepository.findAll();
	}

	@Override
	public Student save(Student student) {

		return studentRepository.save(student);
	}

	@Override
	public Student getStudentById(long id) {

		Student student = studentRepository.findById(id).get();
		if (student == null) {
			throw new RuntimeException("Did not get the id " + id);
		} else {
			return student;
		}

	}

	@Override
	public Student updateTheStudent(Student student) {
		return studentRepository.save(student);
	}

	@Override
	public void deleteById(long id) {
		studentRepository.deleteById(id);
	}

}
