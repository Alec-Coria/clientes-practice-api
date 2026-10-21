package com.practice.cuentasapi.repository;

import com.practice.cuentasapi.entity.CuentaBancaria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CuentaBancariaRepository extends JpaRepository<CuentaBancaria, Long> {
}
