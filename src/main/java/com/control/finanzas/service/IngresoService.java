/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.control.finanzas.service;

import com.control.finanzas.entity.Ingreso;
import com.control.finanzas.entity.Periodicidad;
import com.control.finanzas.repository.IngresoRepository;
import com.control.finanzas.repository.PeriodicidadRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Manuel
 */
@Service
public class IngresoService {
    
    @Autowired
    private IngresoRepository ingresoRepository;
    
    @Autowired
    private PeriodicidadRepository periodicidadRepository;
    
    public Ingreso agregarIngreso(Ingreso ingreso) {
        Periodicidad p = periodicidadRepository.findById(ingreso.getPeriodicidad().getId())
                .orElseThrow(() -> new RuntimeException("No se encontró periodicidad"));
        ingreso.setPeriodicidad(p);
        System.out.println("fegeg " + ingreso.toString());
        return ingresoRepository.save(ingreso);
    }
    
    public List<Ingreso> obtenerTodosIngresos() {
        return ingresoRepository.findAll();
    }
    
    public Optional<Ingreso> obtenerIngreso(Long id) {
        return ingresoRepository.findById(id);
    }
    
    public Ingreso actualizarIngreso(Long id, Ingreso i) {
        return ingresoRepository.findById(id).map(ingreso -> {
            ingreso.setMonto(i.getMonto());
            ingreso.setNombre(i.getNombre());
            ingreso.setPeriodicidad(i.getPeriodicidad());
            return ingresoRepository.save(ingreso);
        }).orElseThrow(() -> new RuntimeException("No se encontró el ingreso"));
    }
    
    public void eliminarIngreso(Long id) {
        ingresoRepository.deleteById(id);
    }
    
}
