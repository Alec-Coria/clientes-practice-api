package com.practice.cuentasapi.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table (name = "cuentas_bancarias")
@Getter
@Setter
@NoArgsConstructor
public class CuentaBancaria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero_cuenta", unique = true, nullable = false, length = 10)
    private String numeroCuenta;

    @Column(name = "titular", nullable = false, length = 100)
    private String titular;

    @Column(name = "saldo", nullable = false, precision = 19, scale = 2)
    private BigDecimal saldo;

    @Column(name = "activa", nullable = false)
    private boolean activa;
}
