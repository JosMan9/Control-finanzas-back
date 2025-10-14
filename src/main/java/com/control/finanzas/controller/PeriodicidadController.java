/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.control.finanzas.controller;

import com.control.finanzas.entity.Periodicidad;
import com.control.finanzas.service.PeriodicidadService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Periodicidad crear(@RequestBody Periodicidad p) {
        System.out.println("nombre " + p.getNombre());
         System.out.println("dias " + p.getDias());
          System.out.println("activo " + p.getActivo());
        return service.agregarPeriodicidad(p);
    }
    
    @GetMapping
    public List<Periodicidad> obtenerTodos() {
        return service.obtenerPeriodicidadTodos();
    }
    
    @GetMapping("/{id}")
    public Periodicidad obtenerId(@PathVariable Long id) {
        return service.obtenerPeriodicadPorId(id).orElseThrow(() -> new RuntimeException("No se encontró ningujna periodicidad"));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Periodicidad> actualizarPeriodicidad(@PathVariable Long id, @RequestBody Periodicidad p) {
        Periodicidad periodicidad = service.actualizarPeriodicidad(id, p);
        return ResponseEntity.ok(periodicidad);
    }
    
    @DeleteMapping("/{id}")
    public void eliminarPeriodicidad(@PathVariable Long id) {
        service.eliminar(id);
    }
    
}
