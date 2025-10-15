/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.control.finanzas.controller;

import com.control.finanzas.dto.ApiResponse;
import com.control.finanzas.entity.Quincena;
import com.control.finanzas.service.QuincenaService;
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
@RequestMapping("/quincena")
public class QuincenaController {
    
    @Autowired
    private QuincenaService qs;
    
    @PostMapping
    public ResponseEntity<ApiResponse<Quincena>> agregarQuincena(@RequestBody Quincena q) {
        Quincena quincena = qs.agregarQuincena(q);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(true, "Se ha creado la quincena", quincena));
    }
    
    @GetMapping
    public ResponseEntity<ApiResponse<List<Quincena>>> obtenerQuincenas() {
        List lista = qs.obtenerQuincenas();
        return ResponseEntity.ok(new ApiResponse<>(true, "Se han obtenido todas las quincenas", lista));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Quincena>> obtenerQuincena(@PathVariable Long id) {
        Optional<Quincena> quincena = qs.obtenerQuincena(id);
        
        if(quincena.isPresent()) {
            return ResponseEntity.ok(new ApiResponse<>(true, "Se obtuvo la quincena correcta", quincena.get()));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(false, "No se encontró la quincena", null));
    }
    
     @PutMapping("/{id}")
     public ResponseEntity<ApiResponse<Quincena>> actualizarQuincena(@PathVariable Long id, @RequestBody Quincena q) {
         Optional<Quincena> quincena = qs.actualizarQuincena(id, q);
         
         if(quincena.isPresent()) {
             return ResponseEntity.ok(new ApiResponse<>(true, "Se actualizó la quincena correcta", quincena.get()));
         }
         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(false, "No se encontró la quincena", null));
         
     }
    
     @DeleteMapping("/{id}")
     public ResponseEntity<ApiResponse<Void>> eliminarQuincena(@PathVariable Long id) {
         boolean flag = qs.eliminarQuincena(id);
         
         if(flag) {
             return ResponseEntity.ok(new ApiResponse<>(true, "Se eliminó correctamente la quincena", null));
         }
         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(false, "No se encontró la quincena", null));
     }
}
