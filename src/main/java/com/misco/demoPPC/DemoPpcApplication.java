package com.misco.demoPPC;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class DemoPpcApplication {
	@Autowired
	StudentService studentService;

	private int id = 0;

	public static void main(String[] args) {
		SpringApplication.run(DemoPpcApplication.class, args);
		System.out.println("hello world");
	}

	@GetMapping("/hello")
	public String hello() {
		String html = String.format("Pincha en el boton para una SORPRESA");
		html += " <form action=\"/sorpresa\"><p> <input type=\"text\" name=\"nombre\" placeholder=\"Pon tu nombre\" /></p>";
		html += " <input type=\"submit\" value=\"PINCHA\" /></form>";
		html += " <form action=\"/studentadd\"><p> <input type=\"text\" name=\"name\" placeholder=\"Nombre de estudiante a añadir\" /></p>";
		html += " <input type=\"submit\" value=\"Añadir\" /></form>";

		return html;
	}

	@GetMapping("/sorpresa")
	public String sorpresa(@RequestParam(value = "nombre", defaultValue = "ANONIMO") String name) {
		String html = "<h1> Hola " + name + " </h1>";
		html += "<img src=\"foto.jpeg\">";
		html += "<h2> Admitelo, no te esperabas aqui un dibujo de Humberto</h2>";
		return html;
	}

	// creating post mapping that post the student detail in the database
	@GetMapping("/studentadd")
	public String saveStudent(@RequestParam(value = "name", defaultValue="Humberto") String name) {
		Student s = new Student();
		s.setId(id);
		id++;
		s.setName(name);
		studentService.saveOrUpdate(s);
		return "<h1>ESTUDIANTE " +s.getName()+" AÑADIDO</h1>";
	}

	// // creating a get mapping that retrieves all the students detail from the
	// // database
	// @GetMapping("/students")
	// private List<Student> getAllStudent() {
	// 	return studentService.getAllStudent();
	// }

	// // creating a get mapping that retrieves the detail of a specific student
	// @GetMapping("/student/{id}")
	// private Student getStudent(@PathVariable("id") int id) {
	// 	return studentService.getStudentById(id);
	// }

	// // creating a delete mapping that deletes a specific student
	// @DeleteMapping("/student/{id}")
	// private void deleteStudent(@PathVariable("id") int id) {
	// 	studentService.delete(id);
	// }



}
