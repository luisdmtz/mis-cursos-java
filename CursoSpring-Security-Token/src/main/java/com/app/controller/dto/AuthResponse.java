package com.app.controller.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"username", "message", "status", "jwt"}) // DEFINIR ORDEN DE RESPUESTA
public record AuthResponse( // DEFINIR COMO record PARA NO COLOCAR GETTER, SETTERS
        String username,
        String message,
        String jwt,
        Boolean status) { // NOS DEVUELVE username,message,jwt,status
}
