/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.control.finanzas.controller;

import com.control.finanzas.dto.ApiResponse;
import com.control.finanzas.entity.PagoRequest;
import com.control.finanzas.entity.PrestamoDeuda;
import com.control.finanzas.service.PrestamoDeudaService;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
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
@RequestMapping("/prestamo-deuda")
public class PrestamoDeudaController {
    
    @Autowired
    private PrestamoDeudaService deudaService;
    
    @PostMapping
    public ResponseEntity<ApiResponse<PrestamoDeuda>> agregarPrestamoDeuda(@RequestBody PrestamoDeuda pd) {
        PrestamoDeuda deuda = deudaService.agregarPrestamoDeuda(pd);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(true, "Se ha registrado la deuda con éxito", deuda));
    }
    
    @PostMapping("/{id}/pago")
    public ResponseEntity<ApiResponse<?>> pagar(@PathVariable Long id, @Valid @RequestBody PagoRequest request) {
        deudaService.pagarDeuda(id, request.getPago());
        Optional<PrestamoDeuda> deuda = deudaService.obtenerPrestamoDeuda(id);
        BigDecimal cantidadAcumulada = deuda.get().getCantidadAcumulada();
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(true, "Se ha registrado el pado correctamente", "Cantidad acumulada: " + cantidadAcumulada.toString()));
    }
    
    @GetMapping
    public ResponseEntity<ApiResponse<List<PrestamoDeuda>>> obtenerPrestamosDeuda() {
        List lista = deudaService.obtenerPrestamosDeuda();
        return ResponseEntity.ok(new ApiResponse<>(true, "Se han obtenido todos los prestamos deuda", lista));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PrestamoDeuda>> obtenerPrestamoDeudaPorId(@PathVariable Long id) {
        Optional<PrestamoDeuda> deuda = deudaService.obtenerPrestamoDeuda(id);
        
        if(deuda.isPresent()) {
            return ResponseEntity.ok(new ApiResponse<>(true, "Se ha obtenido el préstamo deuda", deuda.get()));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(false, "No se encontró el préstamo deuda", null));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<PrestamoDeuda>> actualizarPrestamoDeuda(@PathVariable Long id, @RequestBody PrestamoDeuda deuda) {
        Optional<PrestamoDeuda> prestamo = deudaService.actualizarPrestamoDeuda(id, deuda);
        
        if(prestamo.isPresent()) {
            return ResponseEntity.ok(new ApiResponse<>(true, "Se ha actualizado el préstamo", prestamo.get()));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(false, "No se encontró el présstamo", null));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<PrestamoDeuda>> eliminarPrestamoDeuda(@PathVariable Long id) {
        boolean flag = deudaService.eliminarPrestamo(id);
        if(flag) {
            return ResponseEntity.ok(new ApiResponse<>(true, "Se eliminó el préstamo deuda", null));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(false, "No se encontró el préstamo deuda", null));
    }
}
