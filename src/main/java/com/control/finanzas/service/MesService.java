/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.control.finanzas.service;

import com.control.finanzas.entity.Mes;
import com.control.finanzas.repository.MesRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Manuel
 */
@Service
public class MesService {
    
    @Autowired
    private MesRepository mesRepository;
    
    public Mes agregarMes(Mes mes) {
        return mesRepository.save(mes);
    }
    
     public List<Mes> obtenerMeses() {
        return mesRepository.findAll();
    }
    
    public Optional<Mes> obtenerPorId(Long id) {
        return mesRepository.findById(id);
    }
    
    public Optional<Mes> actualizarMes(Long id, Mes datosActualizados) {
        return mesRepository.findById(id).map(mes -> {
            mes.setNombreMes(datosActualizados.getNombreMes());
            return mesRepository.save(mes);
        });
    }
    
    public boolean eliminar(Long id) {
        if(mesRepository.existsById(id)) {
            mesRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
