/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.control.finanzas.controller;

import com.control.finanzas.dto.ApiResponse;
import com.control.finanzas.entity.Gasto;
import com.control.finanzas.service.GastoService;
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
@RequestMapping("/gasto")
public class GastoController {
    
    @Autowired
    private GastoService gastoService;
    
    @PostMapping
    public ResponseEntity<ApiResponse<Gasto>> agregarGasto(@RequestBody Gasto gasto) {
        Gasto g = gastoService.agregarGasto(gasto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(true, "Se agregó el gasto correctamente", g));
    }
    
    @GetMapping
    public ResponseEntity<ApiResponse<List<Gasto>>> obtenerGastos() {
        List lista = gastoService.obtenerTodosGsstoss();
        return ResponseEntity.ok(new ApiResponse<>(true, "Se han obtenido todos los gastos", lista));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Gasto>> obtenerGastoPorId(@PathVariable Long id) {
        Optional<Gasto> g = gastoService.obtenerGastoPorId(id);
        
        if(g.isPresent()) {
            return ResponseEntity.ok(new ApiResponse<>(true, "Se ha obtenido el gasto", g.get()));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(false, "No se ha encontrado el gasto", null));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Gasto>> actualizarGasto(@PathVariable Long id, @RequestBody Gasto gasto) {
        Optional<Gasto> g = gastoService.actualizarGasto(id, gasto);
        
        if(g.isPresent()) {
            return ResponseEntity.ok(new ApiResponse<>(true, "Se ha actualizado el gasto", g.get()));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(false, "No se ha encontrado el gasto", null));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminarGasto(@PathVariable Long id) {
        boolean flag = gastoService.eliminarGasto(id);
        
        if(flag) {
            return ResponseEntity.ok(new ApiResponse<>(true, "Se ha eliminado el gasto", null));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(false, "No se ha encontrado el gasto", null));
    }
    
}
