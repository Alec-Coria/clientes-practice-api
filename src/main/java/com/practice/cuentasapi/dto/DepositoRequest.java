package com.practice.cuentasapi.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record DepositoRequest(
        @NotNull
        @Positive
        @Digits(integer = 17, fraction = 2)
        BigDecimal cantidadDepositada
) {
}
