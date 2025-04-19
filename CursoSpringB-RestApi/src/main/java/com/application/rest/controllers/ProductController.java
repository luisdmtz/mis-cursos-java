package com.application.rest.controllers;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

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

import com.application.rest.controllers.dto.ProductDTO;
import com.application.rest.entities.Product;
import com.application.rest.service.IProductService;

@RestController
@RequestMapping("/api/product")
public class ProductController {

	//inyectar las dependencias de implementaciones product Service
	@Autowired
	private IProductService productService;
	
	@GetMapping("/find/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id){
		Optional<Product> prodOptional = productService.findById(id);
		
		if(prodOptional.isPresent()) {
			Product product = prodOptional.get();
			
			ProductDTO productDTO = ProductDTO.builder()
					.id(product.getId())
					.name(product.getName())
					.price(product.getPrice())
					.maker(product.getMaker())
					.build();
			
			return ResponseEntity.ok(productDTO);
		}
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/findAll")
	public ResponseEntity<?> findAll(){
		
		List<ProductDTO> prodList = productService.findAll()
				.stream()
				.map(product -> ProductDTO.builder()
				.id(product.getId())
				.name(product.getName())
				.price(product.getPrice())
				.maker(product.getMaker())
				.build() )
			.toList();
		
		return ResponseEntity.ok(prodList);
	}

	@PostMapping("/save")
	public ResponseEntity<?> save(@RequestBody ProductDTO productDTO) throws URISyntaxException{
	
		if(productDTO.getName().isBlank()) {
			return ResponseEntity.badRequest().build();
		}
		
		productService.save(Product.builder()
				.name(productDTO.getName())
				.price(productDTO.getPrice())
				.maker(productDTO.getMaker())
				.build() );
		
		return ResponseEntity.created(new URI("/api/product/save")).build();
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<?> update(@PathVariable long id, @RequestBody ProductDTO productDTO) {
		
		Optional<Product> productOptional = productService.findById(id);
		
		if(productOptional.isPresent()) {
			Product product = productOptional.get();
			product.setName(productDTO.getName());
			product.setPrice(productDTO.getPrice());
			product.setMaker(productDTO.getMaker());
			productService.save(product);
			
			return ResponseEntity.ok("registro actualizado");
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		Optional<Product> productOptional = productService.findById(id); // consulta el id en el metodo
		
		if(productOptional.isPresent() ) { // si existen entonces lo elimina / antes era: id != null
			productService.deleteById(id);
			
			return ResponseEntity.ok("Registro eliminado");
		}
		
		return ResponseEntity.badRequest().build(); // de lo contrario retorna que no se pudo procesar
	}
	
	
}







