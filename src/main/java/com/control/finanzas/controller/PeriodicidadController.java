/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.control.finanzas.controller;

import com.control.finanzas.dto.ApiResponse;
import com.control.finanzas.entity.Periodicidad;
import com.control.finanzas.service.PeriodicidadService;
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
@RequestMapping("/periodicidad")
public class PeriodicidadController {
    
    @Autowired
    private PeriodicidadService service;
    
    @PostMapping
    public ResponseEntity<ApiResponse<Periodicidad>> crear(@RequestBody Periodicidad p) {
        Periodicidad periodicidad = service.agregarPeriodicidad(p);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(true, "Se ha creado correctamente la periodicidad", periodicidad));
    }
    
    @GetMapping
    public ResponseEntity<ApiResponse<List<Periodicidad>>> obtenerTodos() {
        List lista = service.obtenerPeriodicidadTodos();
        return ResponseEntity.ok(new ApiResponse<>(true, "Se han obtenido todas las periodicidades", lista));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Periodicidad>> obtenerId(@PathVariable Long id) {
        Optional<Periodicidad> periodicidad = service.obtenerPeriodicadPorId(id);
        
        if(periodicidad.isPresent()) {
            return ResponseEntity.ok(new ApiResponse<>(true, "Se ha obtenido la periodicidad", periodicidad.get()));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(false, "No se encontró la periodicidad", null));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Periodicidad>> actualizarPeriodicidad(@PathVariable Long id, @RequestBody Periodicidad p) {
        Optional<Periodicidad> periodicidad = service.actualizarPeriodicidad(id, p);
        
        if(periodicidad.isPresent()) {
            return ResponseEntity.ok(new ApiResponse<>(true, "Se ha actualizado la periodicidad", periodicidad.get()));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(false, "No se encontró la periodicidad", null));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminarPeriodicidad(@PathVariable Long id) {
        boolean flag = service.eliminar(id);
        
        if(flag) {
            return ResponseEntity.ok(new ApiResponse<>(true, "Se ha eliminado la periodicidad", null));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(false, "No se encontró la periodicidad", null));
    }
    
}
