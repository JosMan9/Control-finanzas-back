/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.control.finanzas.service;

import com.control.finanzas.entity.Status;
import com.control.finanzas.repository.StatusRepository;
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
public class StatusService {
    
    @Autowired
    private StatusRepository repository;
    
    @Transactional
    public Status agregarStatus(Status status) {
        return repository.save(status);
    }
    
    @Transactional(readOnly = true)
    public List<Status> obtenerStatus() {
        return repository.findAll();
    }
    
    @Transactional(readOnly = true)
    public Optional<Status> obtenerStatus(Long id) {
        return repository.findById(id);
    }
    
    @Transactional
    public Optional<Status> actualizarStatus(Long id, Status status) {
        return repository.findById(id).map(s -> {
            s.setColor(status.getColor());
            s.setNombre(status.getNombre());
            return s;
        });
    }
    
    @Transactional
    public boolean eliminarStatus(Long id) {
        if(repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
    
}
