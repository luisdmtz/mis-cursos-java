package com.app.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
	//@PreAuthorize("denyAll()") // POR DEFECTO RECHAZA TODOS LOS ENDPOINTS
public class TestAuthController {

    @GetMapping("/hello") // MI EJEMPLO
    //@PreAuthorize("hasAuthority('READ')") //DEFINIR EL PERMISO: CREATE, READ, DELETE, UPDATE, REFACTOR
    public String hello(){ 
        return "Hello EJEMPLO - GET";
    }
    
    
    @GetMapping("/get")
    public String helloGet(){ // ACCESO PUBLICO
        return "Hello World - GET";
    }

    @PostMapping("/post")
    public String helloPost(){ // ACCESO POR ROL "ADMIN", "DEVELOPER"
        return "Hello World - POST";
    }
    
    @PostMapping("/postper")
    public String helloPostPer(){ // ACCESO PERMISOS "ADMIN", "DEVELOPER"
        return "Hello World - POST";
    }

    @PutMapping("/put")
    public String helloPut(){
        return "Hello World - PUT";
    }

    @DeleteMapping("/delete")
    public String helloDelete(){
        return "Hello World - DELETE";
    }

    @PatchMapping("/patch")
    public String helloPatch(){ // ACCESO REFACTOR
        return "Hello World - PATCH";
    }
}
