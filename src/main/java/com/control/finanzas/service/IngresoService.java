/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.control.finanzas.service;

import com.control.finanzas.entity.Ingreso;
import com.control.finanzas.entity.Periodicidad;
import com.control.finanzas.entity.Quincena;
import com.control.finanzas.repository.IngresoRepository;
import com.control.finanzas.repository.PeriodicidadRepository;
import com.control.finanzas.repository.QuincenaRepository;
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
    private QuincenaRepository quincenaRepository;
    
    @Autowired
    private PeriodicidadRepository periodicidadRepository;
    
    public Ingreso agregarIngreso(Ingreso ingreso) {
        Periodicidad p = periodicidadRepository.findById(ingreso.getPeriodicidad().getId())
                .orElseThrow(() -> new RuntimeException("No se encontró periodicidad"));
        Quincena q = quincenaRepository.findById(ingreso.getQuincena().getId())
                .orElseThrow(() -> new RuntimeException("No se encontró la quincena"));
        ingreso.setPeriodicidad(p);
        return ingresoRepository.save(ingreso);
    }
    
    public List<Ingreso> obtenerTodosIngresos() {
        return ingresoRepository.findAll();
    }
    
    public Optional<Ingreso> obtenerIngreso(Long id) {
        return ingresoRepository.findById(id);
    }
    
    public Optional<Ingreso> actualizarIngreso(Long id, Ingreso i) {
        return ingresoRepository.findById(id).map(ingreso -> {
            ingreso.setMonto(i.getMonto());
            ingreso.setNombre(i.getNombre());
            ingreso.setPeriodicidad(i.getPeriodicidad());
            ingreso.setQuincena(i.getQuincena());
            return ingresoRepository.save(ingreso);
        });
    }
    
    public boolean eliminarIngreso(Long id) {
        if(ingresoRepository.existsById(id)) {
            ingresoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
