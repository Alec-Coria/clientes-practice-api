package com.practice.cuentasapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ActualizarTitularRequest(    @NotBlank
                                           @Size(max = 1000)
                                           String titular) {
}
