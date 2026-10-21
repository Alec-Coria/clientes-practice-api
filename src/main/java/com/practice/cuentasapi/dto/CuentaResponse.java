package com.practice.cuentasapi.dto;

import java.math.BigDecimal;

public record CuentaResponse(
        Long id,
        String numeroCuenta,
        String titular,
        BigDecimal saldo,
        boolean activa) { }
