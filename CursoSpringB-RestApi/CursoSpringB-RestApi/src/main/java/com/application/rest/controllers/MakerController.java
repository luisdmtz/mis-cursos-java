package com.application.rest.controllers;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.application.rest.controllers.dto.MakerDTO;
import com.application.rest.entities.Maker;
import com.application.rest.service.IMakerService;

import jakarta.annotation.PostConstruct;

@RestController
@RequestMapping("/api/maker")
public class MakerController {

	@Autowired
	private IMakerService makerService;
	
	@GetMapping("/find/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id){
		Optional<Maker> makerOptional = makerService.findById(id);
		
		if(makerOptional.isPresent()) { // si existe, si lo encontro, retornar el creador maker
			Maker maker = makerOptional.get();
			
			 MakerDTO makerDTO = MakerDTO.builder()
					 .id(maker.getId())
					 .name(maker.getName())
					 .productList(maker.getProductList())
					 .build();
					 
			 return ResponseEntity.ok(makerDTO);
		}
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/findAll")
	public ResponseEntity<?> findAll(){
		
		List<MakerDTO> makerList = makerService.findAll()
				.stream()
				.map(maker -> MakerDTO.builder()
						.id(maker.getId())
						.name(maker.getName())
						.productList(maker.getProductList())
						.build() )
				.toList();
		
		return ResponseEntity.ok(makerList);
	}
	
	@PostMapping("/save")
	public ResponseEntity<?> save(@RequestBody MakerDTO makerDTO) throws URISyntaxException{
		
		if(makerDTO.getName().isBlank()) { // si el nombre esta vacvio vamos a retornar:
			return ResponseEntity.badRequest().build();
		}
				
		makerService.save(Maker.builder()
				.name(makerDTO.getName())
				.build());
		
		return ResponseEntity.created(new URI("/api/maker/save")).build();
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody MakerDTO makerDTO){
		
		Optional<Maker> makerOptional = makerService.findById(id); // validar si ya existe el id
		
		if(makerOptional.isPresent()) { // si ya existe entonces:
			Maker maker = makerOptional.get();
			maker.setName(makerDTO.getName());
			makerService.save(maker);
			
			return ResponseEntity.ok("Registro actualizado");
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		Optional<Maker> makerOptional = makerService.findById(id); // consulta el id en el metodo
		
		if(makerOptional.isPresent() ) { // si existen entonces lo elimina / antes era: id != null
			makerService.deleteById(id);
			
			return ResponseEntity.ok("Registro eliminado");
		}
		
		return ResponseEntity.badRequest().build(); // de lo contrario retorna que no se pudo procesar
	}
	
	
}





