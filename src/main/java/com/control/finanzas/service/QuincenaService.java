/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.control.finanzas.service;

import com.control.finanzas.entity.Quincena;
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
public class QuincenaService {
    
    @Autowired
    private QuincenaRepository quincenaRepository;
    
    public Quincena agregarQuincena(Quincena quincena) {
        return quincenaRepository.save(quincena);
    }
    
    public List<Quincena> obtenerQuincenas() {
        return quincenaRepository.findAll();
    }
    
    public Optional<Quincena> obtenerQuincena(Long id) {
        return quincenaRepository.findById(id);
    }
    
    public Quincena actualizarQuincena(Long id, Quincena q) {
        return quincenaRepository.findById(id).map(quincena -> {
            quincena.setFecha(q.getFecha());
            quincena.setNombre(q.getNombre());
            return quincenaRepository.save(quincena);
        }).orElseThrow(() -> new RuntimeException("No se encontró la quincena"));
    }
    
    public void eliminarQuincena(Long id) {
        quincenaRepository.deleteById(id);
    }
    
    
}
