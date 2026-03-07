/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.control.finanzas.controller;

import com.control.finanzas.dto.ApiResponse;
import com.control.finanzas.entity.Status;
import com.control.finanzas.service.StatusService;
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
@RequestMapping("/status")
public class StatusController {
    
    @Autowired
    private StatusService service;
    
    @PostMapping
    public ResponseEntity<ApiResponse<Status>> agregarStatus(@RequestBody Status status) {
        Status s = service.agregarStatus(status);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(true, "Se ha registrado con éxito el status", s));
    }
    
    @GetMapping
    public ResponseEntity<ApiResponse<List<Status>>> obtenerTodosStatus() {
        List lista = service.obtenerStatus();
        return ResponseEntity.ok(new ApiResponse<>(true, "Se han obtenido todos los status", lista));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Status>> obtenerStatus(@PathVariable Long id) {
        Optional<Status> status = service.obtenerStatus(id);
        
        if(status.isPresent()) {
            return ResponseEntity.ok(new ApiResponse<>(true, "Se obtuvo el status", status.get()));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(false, "No se encontró el status " + id, null));
    }
    
   @PutMapping("/{id}")
   public ResponseEntity<ApiResponse<Status>> actualizarStatus(@PathVariable Long id, @RequestBody Status status) {
       Optional<Status> optional = service.actualizarStatus(id, status);
       
       if(optional.isPresent()) {
           return ResponseEntity.ok(new ApiResponse<>(true, "Se actualizó el status", optional.get()));
       }
       return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(false, "No se encontró el status " + id, null));
   }
   
   @DeleteMapping("/{id}")
   public ResponseEntity<ApiResponse<Status>> eliminarStatus(@PathVariable Long id) {
       boolean flag = service.eliminarStatus(id);
       
       if(flag) {
           return ResponseEntity.ok(new ApiResponse<>(true, "Se eliminó el status", null));
       }
       return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(false, "No se encontró el status con id " + id, null));
   }
}
