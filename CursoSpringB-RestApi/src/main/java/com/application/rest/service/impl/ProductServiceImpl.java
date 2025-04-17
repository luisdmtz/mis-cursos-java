package com.application.rest.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.rest.entities.Product;
import com.application.rest.persistence.IProductDAO;
import com.application.rest.service.IProductService;

@Service
public class ProductServiceImpl implements IProductService{

	@Autowired
	private IProductDAO productDAO;
	
	@Override
	public List<Product> findAll() {
		return productDAO.findAll();
	}

	@Override
	public Optional<Product> findById(Long id) {
		return productDAO.findById(id);
	}

	@Override
	public List<Product> findByIdPriceInRange(BigDecimal minPrice, BigDecimal maxPrice) {
		return productDAO.findByIdPriceInRange(minPrice, maxPrice);
	}

	@Override
	public void save(Product product) {
		productDAO.save(product);
	}

	@Override
	public void deleteById(Long id) {
		productDAO.deleteById(id);
	}

}
