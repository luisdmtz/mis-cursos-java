package cursoSpringBoot.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import cursoSpringBoot.model.Product;


@Service // V3 INICIO INJECCION DE DEPENDENCIAS - SERVICIO QUE ALMACENA EN SU CONTENEDOR 
public class ProductsServiceImpl implements ProductService{ // V2 // AGREGAR implements EN LA INTERFAZ ProductService
	 //public class ProductsServiceImpl { // V1  
	
	 // SIMULA UNA BD
	 List<Product> products = new ArrayList<>(Arrays.asList(
			new Product(1, "Papa", 70.00, 10),
			new Product(2, "Frijol", 10.00, 50),
			new Product(3, "Lechuga", 42.00, 30),
			new Product(4, "Ciruala", 40.00, 20)
			));
	 
	
	 //V1 RETORNA LA LISTA PRODUCTO -> siguiente etapa generar el coÑntrolador: endpoints,etc
	 @Override  // V2 -> SE AGREGA X SOBREESCRITURA DE CODIGO PERO AQUI NO HACE NADA
	 public List<Product> getProducts(){ // V1
		 return products;
	 }
	 
	 
	 
	 
//	 public List<Product> getProductsName(String nombre){
//		 return products;
//	 }
//	 
//	 public Product postProducts(Product product) {
//		 products.add(product);
//		 return product;
//	 }

	 
	 
}








