package com.example.cursoSpringB_Student.domain;

// DOMAIN ES LA CAPA DE LOGICA DE NEGOCIOS

// ESTO ES CLASE POCO: ES PARA REPRESENTAR LA ESTRUCTURA DE UN ALUMNO CON SUS ATRIBUTOS
public class Student {
	
	//atributos de un alumno
	private int id;
	private String name;
	private String email;
	private int age; //edad
	private String course; //curso inscrito
	
	
	//Constructor
	 public Student (int id, String name, String email, int age, String course) {
		 this.id = id;
		 this.name = name;
		 this.email = email;
		 this.age = age;
		 this.course = course;
	 }

	 
    //metodo getter and setter para tengamos acceso o poblar los datos desde el constructor con la clase student, endpoint, ect
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}
	 
	 
	 

}
