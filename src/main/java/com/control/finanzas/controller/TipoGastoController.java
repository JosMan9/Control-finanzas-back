/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.control.finanzas.controller;

import com.control.finanzas.dto.ApiResponse;
import com.control.finanzas.entity.TipoGasto;
import com.control.finanzas.service.TipoGastoService;
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
@RequestMapping("/tipo-gasto")
public class TipoGastoController {
    
    @Autowired
    private TipoGastoService service;
    
    @PostMapping
    public ResponseEntity<ApiResponse<TipoGasto>> agregar(@RequestBody TipoGasto gasto) {
       TipoGasto tipoGasto = service.agregarTipoGasto(gasto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(true, "Tipo de gassto creado correctamente", tipoGasto));
    }
    
    @GetMapping
    public ResponseEntity<ApiResponse<List<TipoGasto>>> obtenerTodos() {
        List lita =  service.obtenerTodosTipoGasto();
        return ResponseEntity.ok(new ApiResponse<>(true, "Lista de tipos de gatos obtenidas correctamente", lita));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<TipoGasto>> obtenerPorId(@PathVariable Long id) {
        Optional<TipoGasto> tipoOptional = service.obtenerTipoGastoById(id);
        
        if(tipoOptional.isPresent()) {
            return ResponseEntity.ok(new ApiResponse<>(true, "Se obtubo el tipo de gasto", tipoOptional.get()));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(false, "No se encontró el tipo de gasto con id " + id, null));
        }

    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<TipoGasto>> actualziar(@PathVariable Long id, @RequestBody TipoGasto gasto) {
        Optional<TipoGasto> g = service.actualizarTipoGasto(id, gasto);
        
        if(g.isPresent()) {
            return ResponseEntity.ok(new ApiResponse<>(true, "Se actualizó el tipo de gasto", g.get()));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(false, "No se encontró el tipo de gasto con id " + id, null));
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminar(@PathVariable Long id) {
       boolean flag =  service.eliminarTipoGsto(id);
       
       if(flag) {
           return ResponseEntity.ok(new ApiResponse<>(true, "Se eliminó el tipo de gasto", null));
       } else {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(false, "No se encontró el tipo de gasto con id " + id, null));
       }
    }
    
}
