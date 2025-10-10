/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.control.finanzas.controller;

import com.control.finanzas.entity.Ingreso;
import com.control.finanzas.service.IngresoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Ingreso agregarIngreso(@RequestBody Ingreso i) {
        return ingresoService.agregarIngreso(i);
    }
    
    @GetMapping
    public List<Ingreso> obtenerTodos() {
        return ingresoService.obtenerTodosIngresos();
    }
    
    @GetMapping("/{id}")
    public Ingreso obtenerIngreso(@PathVariable Long id) {
        return ingresoService.obtenerIngreso(id).orElseThrow(() -> new RuntimeException("No se encontró el ingreso"));
    }
    
    @PutMapping("/{id}")
    public Ingreso actualizarIngreso(@PathVariable Long id, Ingreso i) {
        return ingresoService.actualizarIngreso(id, i);
    }
    
    @DeleteMapping("/{id}")
    public void eliminarIngreso(@PathVariable Long id) {
        ingresoService.eliminarIngreso(id);
    }
    
}
