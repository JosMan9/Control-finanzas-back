/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.control.finanzas.controller;

import com.control.finanzas.entity.Quincena;
import com.control.finanzas.service.QuincenaService;
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
@RequestMapping("/quincena")
public class QuincenaController {
    
    @Autowired
    private QuincenaService qs;
    
    @PostMapping
    public Quincena agregarQuincena(@RequestBody Quincena q) {
        return qs.agregarQuincena(q);
    }
    
    @GetMapping
    public List<Quincena> obtenerQuincenas() {
        return qs.obtenerQuincenas();
    }
    
    @GetMapping("/{id}")
    public Quincena obtenerQuincena(@PathVariable Long id) {
        return qs.obtenerQuincena(id).orElseThrow(() -> new RuntimeException("No se encontró la quincena"));
    }
    
     @PutMapping("/{id}")
     public ResponseEntity<Quincena> actualizarQuincena(@PathVariable Long id, @RequestBody Quincena q) {
         Quincena quincena = qs.actualizarQuincena(id, q);
         return ResponseEntity.ok(quincena);
     }
    
     @DeleteMapping("/{id}")
     public void eliminarQuincena(@PathVariable Long id) {
         qs.eliminarQuincena(id);
     }
}
