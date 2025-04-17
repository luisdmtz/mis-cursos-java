package com.application.rest.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.application.rest.entities.Product;

// ESTAMOS CREANDO UNA ARQUITECTURA DE PERSISTENCIA, LA CAPA DE SERVICE SE ENCARGA DE TODA LA LOGICA DE NEGOCIO
public interface IProductService {
// PEGAR LOS MISMOS METODOS DE IProductDAO

	// METODOS PARA NUESTRO PRODUCTO
    List<Product> findAll(); //listar totdo
	
    Optional<Product> findById(Long id); // metodo para buscar por id
	
	List<Product> findByIdPriceInRange(BigDecimal minPrice, BigDecimal maxPrice); //obtener una lista de producto por el precio en un rango de 1000 - 100 
	
	void save(Product product); //para guardar
	
    void deleteById(Long id); //eliminar por id
	
}
