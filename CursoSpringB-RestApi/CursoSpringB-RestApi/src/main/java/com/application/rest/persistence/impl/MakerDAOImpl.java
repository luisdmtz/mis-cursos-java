package com.application.rest.persistence.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.application.rest.entities.Maker;
import com.application.rest.persistence.IMakerDAO;
import com.application.rest.repository.MakerRepository;

@Component
public class MakerDAOImpl implements IMakerDAO{
	
	@Autowired
	private MakerRepository makerRepository; // implementamos maker repository

	// TODO Auto-generated method stub
	@Override
	public List<Maker> findAll() {
		return (List<Maker>) makerRepository.findAll(); // se modifica metodo listar
	}

	@Override
	public Optional<Maker> findById(Long id) { 
		return makerRepository.findById(id);  // se modifica metodo listar por id
	}

	@Override
	public void save(Maker maker) {
		makerRepository.save(maker);  // se modifica metodo agregar
	}

	@Override
	public void deleteById(Long id) {
		makerRepository.deleteById(id);  // se modifica metodo eliminar
	}

}
