/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.control.finanzas.controller;

import com.control.finanzas.entity.Tarjeta;
import com.control.finanzas.service.TarjetaService;
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
@RequestMapping("/tarjeta")
public class TarjetaController {
    
    @Autowired
    private TarjetaService tarjetaService;
    
    @PostMapping
    public Tarjeta crear(@RequestBody Tarjeta tarjeta) {
        return tarjetaService.agregarTarjeta(tarjeta);
    }
    
    @GetMapping
    public List<Tarjeta> obtenerTarjetas() {
        return tarjetaService.obtenerTarjetas();
    }
    
    @GetMapping("/{id}")
    public Tarjeta obtenerPorId(@PathVariable Long id) {
        return tarjetaService.obtenerPorId(id).orElseThrow(() -> new RuntimeException("Tarjeta no encontrada"));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Tarjeta> actualizar(@PathVariable Long id, @RequestBody Tarjeta tarjeta) {
        Tarjeta t = tarjetaService.actualizarTarjeta(id,tarjeta);
        return ResponseEntity.ok(t);
    }
    
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        tarjetaService.eliminar(id);
    }
    
}
