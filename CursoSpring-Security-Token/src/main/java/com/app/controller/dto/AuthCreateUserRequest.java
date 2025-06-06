package com.app.controller.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public record AuthCreateUserRequest(@NotBlank String username,
                                    @NotBlank String password,
                                    @Valid AuthCreateRoleRequest roleRequest) { // COLOCAR LOS CAMPOS QUE NECESITAMOS
}
