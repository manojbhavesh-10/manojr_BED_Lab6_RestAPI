package com.greatlearning.student.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.greatlearning.student.model.Student;
import com.greatlearning.student.service.StudentService;

@Controller
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private StudentService studentService;

	@RequestMapping("/list")
	public String getStudents(Model model) {
		model.addAttribute("Students", studentService.getAllStudent());
		return "list-Students";
	}

	@RequestMapping("/showFormForAdd")
	public String createStudentFrom(Model model) {
		Student theStudent = new Student();
		model.addAttribute("Student", theStudent);
		return "Student-form";
	}

	@RequestMapping("/showFormForUpdate")
	public String editStudentForm(@RequestParam("studentId") long id, Model model) {
		model.addAttribute("Student", studentService.getStudentById(id));
		return "Student-form";

	}

	@RequestMapping("/save")
	public String updateStudent(@RequestParam("id") long id, @RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName, @RequestParam("course") String course,
			@RequestParam("country") String country) {

		Student theStudent;
		if (id != 0) {
			theStudent = studentService.getStudentById(id);
			theStudent.setFirstName(firstName);
			theStudent.setLastName(lastName);
			theStudent.setCourse(course);
			theStudent.setCountry(country);
		}

		else {
			theStudent = new Student(firstName, lastName, course, country);
		}
		studentService.save(theStudent);

		return "redirect:/student/list";

	}

	@RequestMapping("/delete")
	public String deleteStudent(@RequestParam("studentId") long id) {
		studentService.deleteById(id);
		return "redirect:/student/list";
	}

	@RequestMapping(value = "/403")
	public ModelAndView accessDenied(Principal user) {
		ModelAndView model = new ModelAndView();

		if (user != null) {
			model.addObject("msg", "Hi " + user.getName() + ", you do have have permission to access this page!");
		} else {
			model.addObject("msg", "You do not have permission to acces this page!");
		}

		model.setViewName("403");
		return model;

	}

}
