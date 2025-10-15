/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.control.finanzas.controller;

import com.control.finanzas.dto.ApiResponse;
import com.control.finanzas.entity.Tarjeta;
import com.control.finanzas.service.TarjetaService;
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
@RequestMapping("/tarjeta")
public class TarjetaController {
    
    @Autowired
    private TarjetaService tarjetaService;
    
    @PostMapping
    public ResponseEntity<ApiResponse<Tarjeta>> crear(@RequestBody Tarjeta tarjeta) {
        Tarjeta t = tarjetaService.agregarTarjeta(tarjeta); 
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(true, "Tarjeta creada", t));
    }
    
    @GetMapping
    public ResponseEntity<ApiResponse<List<Tarjeta>>> obtenerTarjetas() {
        List lista = tarjetaService.obtenerTarjetas();
        return ResponseEntity.ok(new ApiResponse<>(true, "Tarjeta obtenidas", lista));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Tarjeta>> obtenerPorId(@PathVariable Long id) {
        Optional<Tarjeta> tarjeta = tarjetaService.obtenerPorId(id);
        
        if(tarjeta.isPresent()) {
            return ResponseEntity.ok(new ApiResponse<>(true, "Se obtuvo la tarjeta", tarjeta.get()));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(false, "No se encontró la tarjeta", null));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Tarjeta>> actualizar(@PathVariable Long id, @RequestBody Tarjeta tarjeta) {
        Optional<Tarjeta> t = tarjetaService.actualizarTarjeta(id,tarjeta);
        
        if(t.isPresent()) {
            return ResponseEntity.ok(new ApiResponse<>(true, "Tarjeta actualizada", t.get()));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(false, "No se encontró la tarjeta", null));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminar(@PathVariable Long id) {
        boolean flag = tarjetaService.eliminar(id);
        
        if(flag) {
            return ResponseEntity.ok(new ApiResponse<>(true, "Se eliminó la tarjeta", null));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(false, "No se encontró la tarjeta", null));
    }
    
}
