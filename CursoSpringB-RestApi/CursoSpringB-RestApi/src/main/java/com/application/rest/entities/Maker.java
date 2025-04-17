package com.application.rest.entities;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity // para decirle a JPA es una entidad

// CREAR LAS TABLAS 
@Table(name = "fabricante")
public class Maker { //CREADOR O FABRICANTE

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // para que sea JPA que genere mi unico registro
	private Long id;
	
	@Column(name = "nombre")
	private String name;
	                       //si en caso hay movimiento en cascada  / fetch lazy no sobrecargue cuando exist el creador     / si elimino un creador tambien elimina los productos
	@OneToMany(mappedBy = "maker", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true) // especificamos que la clase Maker es primero
	@JsonIgnore // ignora la serializacion
	private List<Product> productList = new ArrayList<>();
}









