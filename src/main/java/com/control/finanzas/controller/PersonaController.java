/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.control.finanzas.controller;

import com.control.finanzas.dto.ApiResponse;
import com.control.finanzas.entity.Persona;
import com.control.finanzas.service.PersonaService;
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
@RequestMapping("/persona")
public class PersonaController {
    
    @Autowired
    private PersonaService personaService;
    
    @PostMapping
    public ResponseEntity<ApiResponse<Persona>> agregarPersona(@RequestBody Persona persona) {
        Persona p = personaService.agregarPersona(persona);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(true, "Se ha creado la persona", p));
    }
    
    @GetMapping
    public ResponseEntity<ApiResponse<List<Persona>>> obtenerPersonas() {
        List lista = personaService.obtenerPersonas();
        return ResponseEntity.ok(new ApiResponse<>(true, "Se han obtenido las personas", lista));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Persona>> obtenerPersona (@PathVariable Long id) {
        Optional<Persona> p = personaService.obtenerPersona(id);
        
        if(p.isPresent()) {
            return ResponseEntity.ok(new ApiResponse<>(true, "Se ha obtenido la persona", p.get()));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(false, "No se encontró a la persona", null));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Persona>> actualizarPersona(@PathVariable Long id, @RequestBody Persona persona) {
        Optional<Persona> p = personaService.actualizarPersona(id, persona);
        
        if(p.isPresent()) {
            return ResponseEntity.ok(new ApiResponse<>(true, "Se ha obtenido la persona", p.get()));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(false, "No se encontró a la persona", null));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminarPersona(@PathVariable Long id) {
        boolean flag = personaService.eliminarPersona(id);
        
        if(flag) {
            return ResponseEntity.ok(new ApiResponse<>(true, "Se eliminó la persona", null));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(false, "No se encontró a la persona", null));
    }
}
