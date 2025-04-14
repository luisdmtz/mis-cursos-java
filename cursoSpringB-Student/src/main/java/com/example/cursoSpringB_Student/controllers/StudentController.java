package com.example.cursoSpringB_Student.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cursoSpringB_Student.domain.Student;

//CONTROLLERS ES LA CAPA DE PRESENTACION

@RestController
@RequestMapping("/alumnos")
public class StudentController {
	
	//Lista de alumnos o recursos
	private List<Student> students = new ArrayList<>(Arrays.asList(
			new Student(123,"Luis", "luis@gmail.com",28,"curso spring boot"),
			new Student(234,"juan", "juan@gmail.com",43,"curso java"),
			new Student(344,"cris", "cris@gmail.com",12,"curso pyton"),
			new Student(564,"gilb", "gilb@gmail.com",23,"curso c#")
     ));
	
	// LISTAR ALUMNOS
	@GetMapping
	public List<Student> getStudents(){
		
		return students;
	}

	// LISTAR POR SU EMAIL
	@GetMapping("/{email}")
	public Student getStudents(@PathVariable String email) {
		for(Student s : students) {
			if(s.getEmail().equalsIgnoreCase(email)) {
				return s;
			}
		}
		return null;
	}
	
	// CREAR ALUMNO
	@PostMapping
	public Student postStudent(@RequestBody Student student) {
		students.add(student);
		
		return student;
	}
	
	// MODIFICAR UN ALUMNO
	@PutMapping()
	public Student putStudent(@RequestBody Student student) {
		for(Student s : students) {
			if(s.getId() == student.getId()) {
				s.setName(student.getName());
				s.setEmail(student.getEmail());
				s.setAge(student.getAge());
				s.setCourse(student.getCourse());
				
				return s;
			}
		}
		return null;
	}
	
	// MODIFICAR SOLO ALGUNOS CAMPOS DEL ESTUDIANTE
	@PatchMapping
	public Student patchStudent(@RequestBody Student student) {
		for(Student s : students) {
			if(s.getId() == student.getId()) {
				
				if(student.getName() != null) {
					s.setName(student.getName());
				}
				
				if(student.getEmail() != null) {
					s.setEmail(student.getEmail());
				}

				if(student.getAge() != 0) {
					s.setAge(student.getAge());
				}
				
				if(student.getCourse() != null) {
					s.setCourse(student.getCourse());
				}
				
				return s;
			}
		}
		return null;
	}
	
	//ELIMINAR UN ALUMNO
	@DeleteMapping("/{id}")
	public Student deleteStudent(int id) {
		for(Student s : students) {
			if(s.getId() == id) {
				students.remove(s);
				
				return s;
			}
		}
		return null;
	}
	
}



















