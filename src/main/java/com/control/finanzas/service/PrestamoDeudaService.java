/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.control.finanzas.service;

import com.control.finanzas.entity.Persona;
import com.control.finanzas.entity.PrestamoDeuda;
import com.control.finanzas.repository.PersonaRepository;
import com.control.finanzas.repository.PrestamoDeudaRepository;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Manuel
 */
@Service
public class PrestamoDeudaService {
    
    @Autowired
    private PrestamoDeudaRepository prestamoDeudaRepository;
    
    @Autowired
    private PersonaRepository personaRepository;
    
    public PrestamoDeuda agregarPrestamoDeuda(PrestamoDeuda pd) {
        Persona p = personaRepository.findById(pd.getPersona().getId())
                .orElseThrow(() -> new RuntimeException("No se encontró la persona"));
        pd.setPersona(p);
        return prestamoDeudaRepository.save(pd);
    }
    
    public List<PrestamoDeuda> obtenerPrestamosDeuda() {
        return prestamoDeudaRepository.findAll();
    }
    
    public Optional<PrestamoDeuda> obtenerPrestamoDeuda(Long id) {
        return prestamoDeudaRepository.findById(id);
    }
    
    public Optional<PrestamoDeuda> actualizarPrestamoDeuda(Long id, PrestamoDeuda deuda) {
        return prestamoDeudaRepository.findById(id).map(p -> {
            p.setCantidadAcumulada(deuda.getCantidadAcumulada());
            p.setCantidadPrestada(deuda.getCantidadPrestada());
            p.setPersona(deuda.getPersona());
            p.setFechaPrestamo(deuda.getFechaPrestamo());
            return prestamoDeudaRepository.save(p);
        });
    }
    
    @Transactional
    public Optional<PrestamoDeuda> pagarDeuda(Long id, BigDecimal pago) {
        return prestamoDeudaRepository.findById(id).map(p -> {
            p.pagar(pago);
            return prestamoDeudaRepository.save(p);
        });
    }
    
    public boolean eliminarPrestamo(Long id) {
        if(prestamoDeudaRepository.existsById(id)) {
            prestamoDeudaRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
}
