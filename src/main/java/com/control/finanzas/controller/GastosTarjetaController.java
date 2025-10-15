/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.control.finanzas.controller;

import com.control.finanzas.dto.ApiResponse;
import com.control.finanzas.entity.GastosTarjeta;
import com.control.finanzas.service.GastoTarjetaService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Manuel
 */
@RestController
@RequestMapping("/gasto-tarjeta")
public class GastosTarjetaController {
    
    @Autowired
    private GastoTarjetaService gastoTarjetaService;
    
    @PostMapping
    public ResponseEntity<ApiResponse<GastosTarjeta>> agregarGastoTarejta(@RequestBody GastosTarjeta gastosTarjeta) {
        GastosTarjeta gt = gastoTarjetaService.agregarGastoTarjeta(gastosTarjeta);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(true, "Se ha creado el gasto de tarjeta", gt));
    }
    
    @GetMapping
    public ResponseEntity<ApiResponse<List<GastosTarjeta>>> obtenerTodos() {
        List lista = gastoTarjetaService.obtenerTodos();
        return ResponseEntity.ok(new ApiResponse<>(true, "Se han obtenido todos los gastos de tarjeta", lista));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<GastosTarjeta>> obtenerGastoTarjeta(@PathVariable Long id) {
        Optional<GastosTarjeta> gasto = gastoTarjetaService.obtenerGastoTarjets(id);
       
        if(gasto.isPresent()) {
            return ResponseEntity.ok(new ApiResponse<>(true, "Se obtuvo el gasto de tarjeta", gasto.get()));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(false, "No se encontró el gasto de tarjeta", null));
    }
            
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<GastosTarjeta>> actualizarGastoTarjeta(@PathVariable Long id, @RequestBody GastosTarjeta gt) {
        Optional<GastosTarjeta> gasto = gastoTarjetaService.actualizarGastoTarjeta(id, gt);
        
        if(gasto.isPresent()) {
            return ResponseEntity.ok(new ApiResponse<>(true, "Se actualizó el gasto de tarjeta", gasto.get()));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(false, "No se encontró el gasto de tarjeta", null));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminarGastoTarjeta(@PathVariable Long id) {
        boolean flag = gastoTarjetaService.eliminarGastosTarjeta(id);
        
        if(flag) {
            return ResponseEntity.ok(new ApiResponse<>(true, "Se elimió el gasto de tarjeta", null));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(false, "No se encontró el gasto de tarjeta", null));
    }
}
