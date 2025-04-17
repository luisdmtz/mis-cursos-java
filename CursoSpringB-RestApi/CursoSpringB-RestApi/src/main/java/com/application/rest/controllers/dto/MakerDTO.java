package com.application.rest.controllers.dto;

import java.util.ArrayList;
import java.util.List;

import com.application.rest.entities.Product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MakerDTO { //este metodo es lo que se retorna como respuesta en la list de controller

	private Long id;
	private String name;
	private List<Product> productList = new ArrayList<>();
	
}




