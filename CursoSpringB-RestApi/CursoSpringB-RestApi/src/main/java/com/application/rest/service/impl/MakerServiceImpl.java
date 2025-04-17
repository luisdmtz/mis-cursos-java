package com.application.rest.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.rest.entities.Maker;
import com.application.rest.persistence.IMakerDAO;
import com.application.rest.service.IMakerService;

@Service
public class MakerServiceImpl implements IMakerService{ // implementa metodos
	
	@Autowired
	private IMakerDAO makerDAO;

	// TODO Auto-generated method stub

	@Override
	public List<Maker> findAll() {
		return makerDAO.findAll();
	}

	@Override
	public Optional<Maker> findById(Long id) {
		return makerDAO.findById(id);
	}

	@Override
	public void save(Maker maker) {
		makerDAO.save(maker);
	}

	@Override
	public void deleteById(Long id) {
		makerDAO.deleteById(id);
	}

	
}
