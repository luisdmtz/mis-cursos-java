package cursoSpringBoot.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // anotaciones controller=se define anotaciones
public class HolaMundoRestController {

	@GetMapping({"saludo", "/hw", "/hola"}) //anotacion  Get Endpoint o servicio
	public String saludo() {
		
		System.out.println("Solicitud ejecutada"); //muestra en consola
		
		return "Hola Mundo Spring Boot"; //retorna una vista web
	}
}
