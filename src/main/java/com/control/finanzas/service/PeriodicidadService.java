/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.control.finanzas.service;

import com.control.finanzas.entity.Periodicidad;
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
public class PeriodicidadService {
    
    @Autowired
    private PeriodicidadRepository periodicidadRepository;
    
    public Periodicidad agregarPeriodicidad(Periodicidad p) {
        return periodicidadRepository.save(p);
    }
    
    public List<Periodicidad> obtenerPeriodicidadTodos() {
        return periodicidadRepository.findAll();
    }
    
    public Optional<Periodicidad> obtenerPeriodicadPorId(Long id) {
        return periodicidadRepository.findById(id);
    }
    
    public Optional<Periodicidad> actualizarPeriodicidad(Long id, Periodicidad p) {
        return periodicidadRepository.findById(id).map(periodicidad -> {
            periodicidad.setNombre(p.getNombre());
            periodicidad.setActivo(p.getActivo());
            periodicidad.setDias(p.getDias());
            return periodicidadRepository.save(periodicidad);
        });
    }
    
    public boolean eliminar(Long id) {
        if(periodicidadRepository.existsById(id)) {
            periodicidadRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
