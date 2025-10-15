/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.control.finanzas.service;

import com.control.finanzas.entity.Tarjeta;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.control.finanzas.repository.TarjetaRepository;

/**
 *
 * @author Manuel
 */
@Service
public class TarjetaService {
    
    @Autowired
    private TarjetaRepository tarjetaRepository;
    
    public Tarjeta agregarTarjeta(Tarjeta tarjeta) {
        return tarjetaRepository.save(tarjeta);
    }
    
    public List<Tarjeta> obtenerTarjetas() {
        return tarjetaRepository.findAll();
    }
    
    public Optional<Tarjeta> obtenerPorId(Long id) {
        return tarjetaRepository.findById(id);
    }
    
    public Optional<Tarjeta> actualizarTarjeta(Long id, Tarjeta datosActualizados) {
        return tarjetaRepository.findById(id).map(tarjeta -> {
            tarjeta.setNombre(datosActualizados.getNombre());
            tarjeta.setDiaCorte(datosActualizados.getDiaCorte());
            tarjeta.setDiaPago(datosActualizados.getDiaPago());
            return tarjetaRepository.save(tarjeta);
        });
    }
    
    public boolean eliminar(Long id) {
        if(tarjetaRepository.existsById(id)) {
            tarjetaRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
}
