package com.practice.cuentasapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CuentaRequest(
        @NotBlank
        @Pattern(regexp = "\\d{10}")
        String numeroCuenta,
        @NotBlank
        @Size(max = 100)
        String titular
) { }
