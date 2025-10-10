/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.control.finanzas.controller;

import com.control.finanzas.entity.TipoGasto;
import com.control.finanzas.service.TipoGastoService;
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
@RequestMapping("/tipo-gasto")
public class TipoGastoController {
    
    @Autowired
    private TipoGastoService service;
    
    @PostMapping
    public TipoGasto agregar(@RequestBody TipoGasto gasto) {
        return service.agregarTipoGasto(gasto);
    }
    
    @GetMapping
    public List<TipoGasto> obtenerTodos() {
        return service.obtenerTodosTipoGasto();
    }
    
    @GetMapping("/{id}")
    public TipoGasto obtenerPorId(@PathVariable Long id) {
        return service.obtenerTipoGastoById(id);
    }
    
    @PutMapping("/{id}")
    public TipoGasto actualziar(@PathVariable Long id, TipoGasto gasto) {
        return service.actualizarTipoGasto(id, gasto);
    }
    
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminarTipoGsto(id);
    }
    
}
