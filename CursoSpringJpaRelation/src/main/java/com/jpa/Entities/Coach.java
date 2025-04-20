package com.jpa.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity

// @Table(name = "tabla") // para colocar un nombre personalizado
public class Coach { // Entrenador - este es el nombre de la tabla real

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	
	@Column(name = "last_name") // para buenas practicas colocar la anotacion Colum, se creara en sql
	private String LastName;
    private String nationality;
    private Integer age;
}




