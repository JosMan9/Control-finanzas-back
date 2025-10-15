/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.control.finanzas.controller;

import com.control.finanzas.dto.ApiResponse;
import com.control.finanzas.entity.Ingreso;
import com.control.finanzas.service.IngresoService;
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
@RequestMapping("/ingreso")
public class IngresoController {
    
    @Autowired
    private IngresoService ingresoService;
    
    @PostMapping
    public ResponseEntity<ApiResponse<Ingreso>> agregarIngreso(@RequestBody Ingreso i) {
        Ingreso ingreso = ingresoService.agregarIngreso(i);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(true, "Se ha creado el ingreso", ingreso));
    }
    
    @GetMapping
    public ResponseEntity<ApiResponse<List<Ingreso>>> obtenerTodos() {
        List lista = ingresoService.obtenerTodosIngresos();
        return ResponseEntity.ok(new ApiResponse<>(true, "Se han obtenido los ingresos", lista));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Ingreso>> obtenerIngreso(@PathVariable Long id) {
        Optional<Ingreso> ingreso = ingresoService.obtenerIngreso(id);
        
        if(ingreso.isPresent()) {
            return ResponseEntity.ok(new ApiResponse<>(true, "Se ha obtenido el ingreso", ingreso.get()));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(false, "No se encontró el ingreso", null));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Ingreso>> actualizarIngreso(@PathVariable Long id, @RequestBody Ingreso i) {
        Optional<Ingreso> ingreso = ingresoService.actualizarIngreso(id, i);
        
        if(ingreso.isPresent()) {
            return ResponseEntity.ok(new ApiResponse<>(true, "Se ha actualizado el ingreso", ingreso.get()));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(false, "No se encontró el ingreso", null));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminarIngreso(@PathVariable Long id) {
        boolean flag = ingresoService.eliminarIngreso(id);
        
        if(flag) {
            return ResponseEntity.ok(new ApiResponse<>(true, "Se ha eliminado el ingreso", null));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(false, "No se encontró el ingreso", null));
    }
}
