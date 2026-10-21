package com.practice.cuentasapi.service;

import com.practice.cuentasapi.dto.ActualizarTitularRequest;
import com.practice.cuentasapi.dto.CuentaRequest;
import com.practice.cuentasapi.dto.CuentaResponse;
import com.practice.cuentasapi.dto.DepositoRequest;
import com.practice.cuentasapi.entity.CuentaBancaria;
import com.practice.cuentasapi.repository.CuentaBancariaRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CuentaBancariaService {
    private final CuentaBancariaRepository cuentaBancariaRepository;
    public CuentaBancariaService(CuentaBancariaRepository cuentaBancariaRepository){
        this.cuentaBancariaRepository = cuentaBancariaRepository;
    }

    public CuentaResponse crearCuentaBancaria (CuentaRequest datosCuentaNueva){
        CuentaBancaria cuentaBancaria = new CuentaBancaria();
        cuentaBancaria.setNumeroCuenta(datosCuentaNueva.numeroCuenta());
        cuentaBancaria.setTitular(datosCuentaNueva.titular());
        cuentaBancaria.setActiva(true);
        //cuentaBancaria.setSaldo(BigDecimal.valueOf(0.0));
        cuentaBancaria.setSaldo(BigDecimal.ZERO.setScale(2));
        return toResponse(cuentaBancariaRepository.save(cuentaBancaria));
    }

    public CuentaResponse getCuentaBancaria (Long id){
        return toResponse(encontrarCuentaBancariaPorId(id));
    }

    public List<CuentaResponse> getCuentasBancarias () {
        return cuentaBancariaRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public CuentaResponse actualizarTituloCuentaBancaria (ActualizarTitularRequest cuenta, Long id){
        CuentaBancaria cuentaParaActualizar = encontrarCuentaBancariaPorId(id);
        cuentaParaActualizar.setTitular(cuenta.titular());
        return toResponse(cuentaBancariaRepository.save(cuentaParaActualizar));
    }

    @Transactional
    public CuentaResponse deposito (DepositoRequest depositoRequest, Long id){
        CuentaBancaria cuentaDeposito = encontrarCuentaBancariaPorId(id);
        if(!cuentaDeposito.isActiva()){
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "No se pueden realizar depósitos en una cuenta inactiva"
            );
        }
        cuentaDeposito.setSaldo(cuentaDeposito.getSaldo().add(depositoRequest.cantidadDepositada()));
        return toResponse(cuentaBancariaRepository.save(cuentaDeposito));
    }

    private CuentaBancaria encontrarCuentaBancariaPorId(Long id){
        return cuentaBancariaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Cuenta bancaria no encontrada"
                ));
    }

    private CuentaResponse toResponse (CuentaBancaria cuentaBancaria){
        return new CuentaResponse(
                cuentaBancaria.getId(),
                cuentaBancaria.getNumeroCuenta(),
                cuentaBancaria.getTitular(),
                cuentaBancaria.getSaldo(),
                cuentaBancaria.isActiva()
        );
    }

}
