/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.control.finanzas.service;

import com.control.finanzas.entity.Persona;
import com.control.finanzas.repository.PersonaRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Manuel
 */
@Service
public class PersonaService {
    
    @Autowired
    private PersonaRepository personaRepository;
    
    public Persona agregarPersona(Persona persona) {
        return personaRepository.save(persona);
    }
    
    public Optional<Persona> obtenerPersona(Long id) {
        return personaRepository.findById(id);
    }
    
    public List<Persona> obtenerPersonas() {
        return personaRepository.findAll();
    }

    public Optional<Persona> actualizarPersona(Long id, Persona persona) {
        return personaRepository.findById(id).map(p -> {
            p.setNombre(persona.getNombre());
            p.setApellidoPaterno(persona.getApellidoPaterno());
            p.setApellidoMaterno(persona.getApellidoMaterno());
            return personaRepository.save(p);
        });
    }
    
    public boolean eliminarPersona(Long id) {
        if(personaRepository.existsById(id)) {
            personaRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
}
