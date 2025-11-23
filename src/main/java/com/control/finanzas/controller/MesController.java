/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.control.finanzas.controller;

import com.control.finanzas.dto.ApiResponse;
import com.control.finanzas.entity.Mes;
import com.control.finanzas.service.MesService;
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
@RequestMapping("/mes")
public class MesController {
    
    @Autowired
    private MesService mesService;
    
    @PostMapping
    public ResponseEntity<ApiResponse<Mes>> crearMes(@RequestBody Mes mes) {
        Mes t = mesService.agregarMes(mes); 
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(true, "Mes creado", t));
    }
    
    @GetMapping
    public ResponseEntity<ApiResponse<List<Mes>>> obtenerMeses() {
        List lista = mesService.obtenerMeses();
        return ResponseEntity.ok(new ApiResponse<>(true, "Meses obtenidos", lista));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Mes>> obtenerPorId(@PathVariable Long id) {
        Optional<Mes> mes = mesService.obtenerPorId(id);
        
        if(mes.isPresent()) {
            return ResponseEntity.ok(new ApiResponse<>(true, "Se obtuvo la tarjeta" + id, mes.get()));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(false, "No se encontró el mes", null));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Mes>> actualizar(@PathVariable Long id, @RequestBody Mes mes) {
        Optional<Mes> t = mesService.actualizarMes(id,mes);
        
        if(t.isPresent()) {
            return ResponseEntity.ok(new ApiResponse<>(true, "Mes actualizado", t.get()));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(false, "No se encontró el mes", null));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminar(@PathVariable Long id) {
        boolean flag = mesService.eliminar(id);
        
        if(flag) {
            return ResponseEntity.ok(new ApiResponse<>(true, "Se eliminó el mes", null));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(false, "No se encontró el mes", null));
    }
}
