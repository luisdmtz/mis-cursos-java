package cursoSpringBoot.controllers;

import cursoSpringBoot.model.Customer;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController // ANOTACION CONTROLADOR DE TIPO REST
@RequestMapping("/clientes") // ANOTACION PARA UNIFICAR LA RUTA DE EDPOINTS A NIVEL DE CLASE Y A NIVEL METODO 
public class CustomerController {

	// CREAR DATOS FICTICIOS PARA LA APPI REST, ASIGNANDO DATOS
	private List<Customer> customers = new ArrayList<>(Arrays.asList(  
			
			new Customer(123, "Luis Dionisio", "luisd", "123456"),
			new Customer(256, "Gerardo Diaz","gerardol", "65165"),
			new Customer(565, "Juan H", "juand", "1298"),
			new Customer(453, "Diego d", "diegod", "651651")
	));
	
	// ANOTACIONES: @GetMapping @PostMapping @PutMapping
	
//---- LISTAR CLIENTES ---- // MAPPEAR NUESTRO METODO, SUFIJO clientes PARA MOSTRAR EN LA URL
	  // forma3--> @RequestMapping(method = RequestMethod.GET) //  recibe solicitudes tipo GET,POST,PUT,ECT
  	@GetMapping // <--forma2  APLICANDO LA ANOTACION @RequestMapping
	  // forma1--> @GetMapping("clientes") 
  	public ResponseEntity<List<Customer>> getCustomers(){ //public List<Customer> getCustomers(){
		
		return ResponseEntity.ok(customers);
		//return customers;
		
	}
	
//---- LISTAR CON PARAMETRO USERNAME ----
     //@RequestMapping(value = "/{username}", method = RequestMethod.GET) 
	@GetMapping("/{username}")   
	 //@GetMapping("clientes/{username}")  
	public ResponseEntity<?> getCliente(@PathVariable String username) { //public Customer getCliente(@PathVariable String username){  // metodo recibe parametro
		for(Customer c : customers) {       //foreach :->en o de | iteracion para repetir varias veces
			if(c.getUsername().equalsIgnoreCase(username)) {  //equalsIgnoreCase para que compare dos cadenas e ignora las direfencias entre mayusculas y minusculas
				return ResponseEntity.ok(c);
				//return c;
			}
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente no encontrado, username: "+ username); //codigo de respuesta 404=NOT_FOUND
		//return null;
	}
	
//---- REGISTRAR ----
	 // @RequestMapping(method = RequestMethod.POST)
	@PostMapping 
	 // @PostMapping("/clientes")
	public ResponseEntity<?> postCliente(@RequestBody Customer customer) { // public Customer postCliente(@RequestBody Customer customer) {
		customers.add(customer);
		
		// Muestra el identificador o URL en el response de POSTMAN para localizar que registro fue AGREGADO
		URI location = ServletUriComponentsBuilder             // iniciando la construcion de una URI
				.fromCurrentRequest()                          // ejecutar el request
				.path("/{username}")                           // el parametro de busqueda username
				.buildAndExpand(customer.getUsername())        // ejecuta y obtiene del obtento el nombre
				.toUri();                                      // aplica la direcion uri
		return ResponseEntity.created(location).body(customer);// 
		//return ResponseEntity.created(location).build();     // responde creando ruta url registrado con parametro username		
		//return ResponseEntity.status(HttpStatus.CREATED).body("cliente creado exitosamente con el id: "+ customer.getID());
		//return customer;
	}
	
//---- ACTUALIZAR ----
	 // @RequestMapping(method = RequestMethod.PUT)
	@PutMapping 
	 // @PutMapping("/clientes")
	public ResponseEntity<?> putCliente(@RequestBody Customer customer) { // public Customer putCliente(@RequestBody Customer customer) {
		for(Customer c : customers) {
			if(c.getID()==customer.getID()) { // COMPARAR ID PARAMETRO CON ID DE LOS ATRIBUTOS 
				c.setName(customer.getName());
				c.setUsername(customer.getUsername());
				c.setPassword(customer.getPassword());
				
				return ResponseEntity.noContent().build(); // RESPUESTA EXITOSA SIN CONTENIDO
				//return ResponseEntity.ok("Cliente modificado exitosamente con el ID: "+customer.getID());
				//return c;
			}
		}
		return ResponseEntity.notFound().build();
		//return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente no encontrado con ID "+ customer.getID());
		//return null;
	}
	
//---- ELIMINAR ----
	 // @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@DeleteMapping("{id}") 
	 // @DeleteMapping("/clientes/{id}")
	public ResponseEntity<?> deleteCliente(@PathVariable int id) { // public Customer deleteCliente(@PathVariable int id) {
		for(Customer c : customers) {
			if(c.getID() == id) {
				customers.remove(c);
				
				return ResponseEntity.noContent().build();
				//return ResponseEntity.ok("Cliente eliminado exitosamente con ID: "+ c.getID());
				//return c;
			}
		}
		return ResponseEntity.notFound().build();
		//return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente no encontrado con ID: " +id);
		//return null;
	}
	
//---- MODIFICAR UN CAMPO ESPECIFICO DEL CLIENTE ----  EN POSTMAN SE MANDA SOLO EL CAMPO QUE QUIERO MODIFICAR
	 // @RequestMapping(method = RequestMethod.PATCH)
	@PatchMapping 
	 // @PatchMapping("/clientes")
	public ResponseEntity<?> patchCliente(@RequestBody Customer customer) { //public Customer patchCliente(@RequestBody Customer customer) {
		for(Customer c : customers) {
			if(c.getID() == customer.getID()) {  // se obtiene el ID de nuestro objeto y comparar con el id customer PARA OBTEJER EL ID QUE QUEREMOS MODIFICAR
				
				if(customer.getName() != null) {  // obtiene el nombre por parametro customer y se compara es diferente de null, entonces obtiene el nombre 
					c.setName(customer.getName());   // entonces lo coloca dentro del nombre
				}
				if(customer.getUsername() != null) {  
					c.setUsername(customer.getUsername());
				}
				if(customer.getPassword() != null) {
					c.setPassword(customer.getPassword());
				}
				
					// Crear el mensaje de respuesta
				        //Map<String, Object> respuesta = new HashMap<>();
				        //respuesta.put("mensaje", "Objeto creado exitosamente");
				        //respuesta.put("objeto", customer);
				        //return new ResponseEntity<>(respuesta, HttpStatus.OK);
		        				 
				return ResponseEntity.ok("Cliente modificado exitosamente con ID"+ c.getID());
				//return c;
			}
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente no encontrado con ID: "+customer.getID());
		//return null;
	}
	
	
}














