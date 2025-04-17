package com.application.rest.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.application.rest.entities.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long>{

	// METODO PARA LISTAR POR RANGO

    			  // buscar producto por anotacion query con un select
	// OPCION2 POR ANOTACION QUERY
	@Query("SELECT p FROM Product p WHERE p.price BETWEEN ?1 AND ?2")
	List<Product> findProductByPriceInRange(BigDecimal minPrice, BigDecimal maxPrice);   //  findProductByPriceInRange = EXISTE ASTA PRICE RANGE NO OBTIENE NADA
	
	               // buscar producto por el rango de precio entre otro con BETTWEN 
	// OPCION1 POR QUERY METHODS
	List<Product> findProductByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);    // findProductByPriceBetween = EXISTE TODO JUNTO-> find product by price between  
}
