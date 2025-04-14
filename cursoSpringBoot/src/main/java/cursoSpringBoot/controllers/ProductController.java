package cursoSpringBoot.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import cursoSpringBoot.model.Customer;
import cursoSpringBoot.model.Product;
import cursoSpringBoot.service.ProductService;
import cursoSpringBoot.service.ProductsServiceImpl;

// crear los endpoint
@RestController
@RequestMapping("/productos")
public class ProductController {
	
	@Autowired // INYECTA AL CONTENEDOR DE SPRING LA DEPENDENCIA AUTOMATICAMENTE LA INJECCION DE DEPENDENCIAS
	private ProductService producstService; //INSTANCIA DE LA CLASE
	// V2 POLIMORFISMO DINAMICO
	// ProductService productsService = new ProductsServiceImpl(); V2
	// V1 INVOCAR O INSTANCIAR el product service
	//ProductsServiceImpl productsService = new ProductsServiceImpl(); // V1
	
	
	// METODO PARA OBTENER EL RETORNO DE LISTAR PRODUCTOS DE ProductsServiceImpl
	@GetMapping
	public ResponseEntity<?> getProducts(){
		List<Product> products = producstService.getProducts();
		
		return ResponseEntity.ok(products);
	}
	
	
	
	
	
//	// METODO LISTAR CON PARAMETRO NOMBRE
//	@GetMapping("/{nombre}")   
//	public ResponseEntity<?> getProductsName(@PathVariable String nombre) {
//		                                                     //List<Product> products = productsService.getProducts();
//		for(Product c : productsService.getProductsName(nombre)) {     //for(Product c : products) {   
//			if(c.getName().equalsIgnoreCase(nombre)) {  
//				return ResponseEntity.ok(c);
//			}
//		}
//		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado, nombre: "+ nombre); 
//	}
//	
//	// METODO AGREGAR
//	@PostMapping
//	public ResponseEntity<?> postProducts(@RequestBody Product product){
//		productsService.postProducts(product);
//	
//		return ResponseEntity.status(HttpStatus.CREATED).body(product);
//		//return ResponseEntity.status(HttpStatus.CREATED).body("cliente creado exitosamente con el id: "+ product.getNombre());
//	}
	
	
	
}



