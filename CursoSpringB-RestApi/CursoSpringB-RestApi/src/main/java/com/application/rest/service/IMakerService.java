package com.application.rest.service;

import java.util.List;
import java.util.Optional;

import com.application.rest.entities.Maker;

public interface IMakerService {
// PEGAR LOS MISMOS METODOS DE IMakerDAO


	// METODOS PARA NUESTRO MAKER
	List<Maker> findAll(); //listar totdo
	
	Optional<Maker> findById(Long id); // metodo para buscar por id
	
	void save(Maker maker); //para guardar
	
	void deleteById(Long id); //eliminar por id
	
}
