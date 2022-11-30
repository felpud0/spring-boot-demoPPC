package com.misco.demoPPC;

import java.util.stream.Collectors;

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
		String html = new String();
		html += "<br>Disponible en 172.22.21.195/hello</br>";
		html += "<br>Pincha en el boton para una SORPRESA</br>";
		html += " <form action=\"/sorpresa\"><p> <input type=\"text\" name=\"nombre\" placeholder=\"Pon tu nombre\" /></p>";
		html += " <input type=\"submit\" value=\"PINCHA\" /></form>";
		html += "Introduce el nombre de los alumnos";
		html += " <form action=\"/studentadd\"><p> <input type=\"text\" name=\"name\" placeholder=\"Nombre de estudiante a añadir\" /></p>";
		html += " <input type=\"submit\" value=\"Añadir\" /></form>";
		html += " <form action=\"/students\">";
		html += " <input type=\"submit\" value=\"Ver DB\" /></form>";

		return html;
	}

	@GetMapping("/sorpresa")
	public String sorpresa(@RequestParam(value = "nombre", defaultValue = "ANONIMO") String name) {
		String html = "<h1> Hola " + name + " </h1>";
		html += "<img src=\"foto.jpeg\">";
		html += "<h2> Admitelo, no te esperabas aqui un dibujo de Humberto</h2>";
		return html;
	}

	@GetMapping("/studentadd")
	public String saveStudent(@RequestParam(value = "name", defaultValue="Humberto") String name) {
		Student s = new Student();
		s.setId(id);
		id++;
		s.setName(name);
		studentService.saveOrUpdate(s);
		return "<h1>ESTUDIANTE " +s.getName()+" AÑADIDO</h1>";
	}

	@GetMapping("/students")
	private String getAllStudent() {
		StringBuilder sb = new StringBuilder("<h1>Alumnos añadidos</h1>");
		studentService.getAllStudent().stream().forEach(s -> sb.append("<br>"+s.getName()+"</br>"));
		return sb.toString();
	}

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
