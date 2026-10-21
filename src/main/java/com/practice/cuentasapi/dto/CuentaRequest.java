package com.practice.cuentasapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CuentaRequest(
        @NotBlank
        @Pattern(regexp = "\\d{10}", message = "El número de cuenta debe ser de 10 dígitos")
        String numeroCuenta,
        @NotBlank
        @Size(max = 100, message = "El titular debe tener máximo 100 caracteres")
        String titular
) { }
