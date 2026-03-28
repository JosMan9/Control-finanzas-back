/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.control.finanzas.controller;

import com.control.finanzas.dto.ApiResponse;
import com.control.finanzas.entity.BitacoraPrestamo;
import com.control.finanzas.service.BitacoraPrestamoService;
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
@RequestMapping("/bitacora-prestamo")
@RestController
public class BitacoraPrestamoController {
    
    @Autowired
    private BitacoraPrestamoService service;
    
    @PostMapping
    public ResponseEntity<ApiResponse<BitacoraPrestamo>> agregarRegistroBitacora(@RequestBody BitacoraPrestamo bitacora) {
        BitacoraPrestamo bp = service.agregarRegistroBitacora(bitacora);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(true, "Se ha registrado con éxito el registro de la bitácora", bp));
    }
    
    @GetMapping
    public ResponseEntity<ApiResponse<List<BitacoraPrestamo>>> obtenerRegistros() {
        List lista = service.obtenerTodosRegistros();
        return ResponseEntity.ok(new ApiResponse<>(true, "Se han obtenido todos los registros", lista));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<BitacoraPrestamo>> obtenerRegistroBitacora(@PathVariable Long id) {
         Optional<BitacoraPrestamo> bp = service.obtenerRegistroBitaocra(id);
        
        if(bp.isPresent()) {
            return ResponseEntity.ok(new ApiResponse<>(true, "Se obtuvo el registro", bp.get()));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(false, "No se encontró el registro " + id, null));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<BitacoraPrestamo>> actualizarRegistroBitacora(@PathVariable Long id, @RequestBody BitacoraPrestamo prestamo) {
        Optional<BitacoraPrestamo> optional = service.actualizarRegistroBitacora(id, prestamo);
       
       if(optional.isPresent()) {
           return ResponseEntity.ok(new ApiResponse<>(true, "Se actualizó el registro de la bitácora", optional.get()));
       }
       return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(false, "No se encontró el registro de la bitácora " + id, null));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<BitacoraPrestamo>> eliminarRegistro(@PathVariable Long id) {
        boolean flag = service.eliminarRegistroBitacora(id);
       
       if(flag) {
           return ResponseEntity.ok(new ApiResponse<>(true, "Se eliminó el registro de la bitácora", null));
       }
       return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(false, "No se encontró el registra de la bitácora con id " + id, null));
    }
}
