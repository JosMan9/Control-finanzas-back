/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.control.finanzas.service;

import com.control.finanzas.entity.BitacoraPrestamo;
import com.control.finanzas.entity.PrestamoDeuda;
import com.control.finanzas.entity.Quincena;
import com.control.finanzas.entity.Status;
import com.control.finanzas.repository.BitacoraPrestamoRepository;
import com.control.finanzas.repository.PrestamoDeudaRepository;
import com.control.finanzas.repository.QuincenaRepository;
import com.control.finanzas.repository.StatusRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Manuel
 */
@Service
public class BitacoraPrestamoService {
    
    @Autowired
    private BitacoraPrestamoRepository bpr;
    
    @Autowired
    private QuincenaRepository qr;
    
    @Autowired
    private PrestamoDeudaRepository pdr;
    
    @Autowired
    private StatusRepository sr;
    
    public BitacoraPrestamo agregarRegistroBitacora(BitacoraPrestamo prestamo) {
        Quincena quincena = qr.findById(prestamo.getQuincena().getId()).orElseThrow(() -> new RuntimeException("No se encontró la quincena"));
        PrestamoDeuda deuda = pdr.findById(prestamo.getPrestamoDeuda().getId()).orElseThrow(() -> new RuntimeException("No se encontró el préstamo"));
        Status status = sr.findById(prestamo.getStatus().getId()).orElseThrow(() -> new RuntimeException("No se encontró el status"));
        
        prestamo.setPrestamoDeuda(deuda);
        prestamo.setQuincena(quincena);
        prestamo.setStatus(status);
        return bpr.save(prestamo);
    }
    
    public List<BitacoraPrestamo> obtenerTodosRegistros() {
        return bpr.findAll();
    }
    
    public Optional<BitacoraPrestamo> obtenerRegistroBitaocra(Long id) {
        return bpr.findById(id);
    }
    
    public Optional<BitacoraPrestamo> actualizarRegistroBitacora(Long id, BitacoraPrestamo prestamo) {
        return bpr.findById(id).map( bp -> {
            bp.setCantidadAbonada(prestamo.getCantidadAbonada());
            bp.setFechaAccion(prestamo.getFechaAccion());
            bp.setPrestamoDeuda(prestamo.getPrestamoDeuda());
            bp.setQuincena(prestamo.getQuincena());
            bp.setStatus(prestamo.getStatus());
            return bp;
        });
    }
    
    public boolean eliminarRegistroBitacora(Long id) {
        if(bpr.existsById(id)) {
            bpr.deleteById(id);
            return true;
        }
        return false;
    }
    
}
