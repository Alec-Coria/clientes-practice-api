package com.practice.cuentasapi.controller;

import com.practice.cuentasapi.dto.ActualizarTitularRequest;
import com.practice.cuentasapi.dto.CuentaRequest;
import com.practice.cuentasapi.dto.CuentaResponse;
import com.practice.cuentasapi.service.CuentaBancariaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/cuentas-bancarias")
public class CuentaBancariaController {
    private final CuentaBancariaService cuentaBancariaService;
    public CuentaBancariaController (CuentaBancariaService cuentaBancariaService){
        this.cuentaBancariaService = cuentaBancariaService;
    }

    @PostMapping
    public ResponseEntity<CuentaResponse> crearCuenta (@Valid @RequestBody CuentaRequest cuentaRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cuentaBancariaService.crearCuentaBancaria(cuentaRequest));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CuentaResponse> getCuenta (@PathVariable Long id){
        return ResponseEntity.ok(cuentaBancariaService.getCuentaBancaria(id));
    }

    @GetMapping
    public ResponseEntity<List<CuentaResponse>> getCuentas (){
        return ResponseEntity.ok(cuentaBancariaService.getCuentasBancarias());
    }

    @PutMapping (path = "/{id}")
    public ResponseEntity<CuentaResponse> updateCuenta (@Valid @RequestBody ActualizarTitularRequest cuenta, @PathVariable Long id){
        return ResponseEntity.ok(cuentaBancariaService.actualizarTituloCuentaBancaria(cuenta, id));
    }

}
