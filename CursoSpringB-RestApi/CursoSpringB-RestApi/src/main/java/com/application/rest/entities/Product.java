package com.application.rest.entities;

import java.math.BigDecimal;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Entity 

@Table(name = "producto") //TABLA PRODUCTO - UN FABRICANTE PUEDE GENERAR UN PRODUCTO
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nombre")
	private String name;
	
	@Column(name = "precio")
	private BigDecimal price;
	
	@ManyToOne // especifica producto como posterior
	@JoinColumn(name = "id_fabricante", nullable = false) // id, llave foranea en producto, siempre exista relacion
	private Maker maker;
}









