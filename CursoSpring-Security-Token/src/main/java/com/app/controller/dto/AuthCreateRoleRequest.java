package com.app.controller.dto;

import jakarta.validation.constraints.Size;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public record AuthCreateRoleRequest( // LISTAR NUMERO DE ROLES 
        @Size(max = 3, message = "The user cannot have more than 3 roles") List<String> roleListName) { // UN USUARIO MAXIMO DE ROLES PUEDE 3 ROLES
}
