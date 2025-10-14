/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.control.finanzas.controller;

import com.control.finanzas.entity.Gasto;
import com.control.finanzas.service.GastoService;
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
@RequestMapping("/gasto")
public class GastoController {
    
    @Autowired
    private GastoService gastoService;
    
    @PostMapping
    public Gasto agregarGasto(@RequestBody Gasto gasto) {
        System.out.println("refer " + gasto.toString());
        return gastoService.agregarGasto(gasto);
    }
    
    @GetMapping
    public List<Gasto> obtenerGastos() {
        return gastoService.obtenerTodosGsstoss();
    }
    
    @GetMapping("/{id}")
    public Gasto obtenerGastoPorId(@PathVariable Long id) {
        return gastoService.obtenerGastoPorId(id).orElseThrow(() -> new RuntimeException("No se encontró el gasto"));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Gasto> actualizarGasto(@PathVariable Long id, @RequestBody Gasto gasto) {
        Gasto g = gastoService.actualizarGasto(id, gasto);
        return ResponseEntity.ok(g);
    }
    
    @DeleteMapping("/{id}")
    public void eliminarGasto(@PathVariable Long id) {
        gastoService.eliminarGasto(id);
    }
    
}
