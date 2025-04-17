package com.application.rest.persistence.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.application.rest.entities.Product;
import com.application.rest.persistence.IProductDAO;
import com.application.rest.repository.ProductRepository;

@Component
public class ProductDAOImpl implements IProductDAO{

	@Autowired
	private ProductRepository productRepository;
	
	// TODO Auto-generated method stub
	@Override
	public List<Product> findAll() {
		return (List<Product>) productRepository.findAll();
	}

	@Override
	public Optional<Product> findById(Long id) {
		return productRepository.findById(id);
	}

	@Override
	public List<Product> findByIdPriceInRange(BigDecimal minPrice, BigDecimal maxPrice) {
		return productRepository.findProductByPriceInRange(minPrice, maxPrice);
	}

	@Override
	public void save(Product product) {
		productRepository.save(product);
	}

	@Override
	public void deleteById(Long id) {
		productRepository.deleteById(id);
	}

}
