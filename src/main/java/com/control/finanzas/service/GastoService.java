/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.control.finanzas.service;

import com.control.finanzas.entity.Gasto;
import com.control.finanzas.entity.Ingreso;
import com.control.finanzas.entity.Quincena;
import com.control.finanzas.entity.TipoGasto;
import com.control.finanzas.repository.GastoRepository;
import com.control.finanzas.repository.IngresoRepository;
import com.control.finanzas.repository.QuincenaRepository;
import com.control.finanzas.repository.TipoGastoRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Manuel
 */
@Service
public class GastoService {
    
    @Autowired
    private GastoRepository gastoRepository;
    
    @Autowired
    private TipoGastoRepository tipoIngresoGastoRepository;
    
    @Autowired
    private IngresoRepository ingresoRepository;
    
    @Autowired
    private QuincenaRepository quincenaRepository;
    
    public Gasto agregarGasto(Gasto g) {
        TipoGasto tipoGasto = tipoIngresoGastoRepository.findById(g.getTipoGasto().getId())
                .orElseThrow(() -> new RuntimeException("No se encontró el tipo de gasto"));
        Ingreso ingreso = ingresoRepository.findById(g.getIngreso().getId()).
                orElseThrow(() -> new RuntimeException("No se encontró el ingreso"));
        Quincena q = quincenaRepository.findById(g.getQuincena().getId())
                .orElseThrow(() -> new RuntimeException("No se encontró la quincena"));
        g.setIngreso(ingreso);
        g.setTipoGasto(tipoGasto);
        g.setQuincena(q);
        return gastoRepository.save(g);
    }
    
    public List<Gasto> obtenerTodosGsstoss() {
        return gastoRepository.findAll();
    }
    
    public Optional<Gasto> obtenerGastoPorId(Long id) {
        return gastoRepository.findById(id);
    }
    
    public Gasto actualizarGasto(Long id, Gasto g) {
        return gastoRepository.findById(id).map(gasto -> {
            gasto.setConcepto(g.getConcepto());
            gasto.setEsCubierto(g.getEsCubierto());
            gasto.setFechaOperacion(g.getFechaOperacion());
            gasto.setIngreso(g.getIngreso());
            gasto.setMonto(g.getMonto());
            gasto.setTipoGasto(g.getTipoGasto());
            gasto.setQuincena(g.getQuincena());
            return gastoRepository.save(gasto);
        }).orElseThrow(() -> new RuntimeException("No se encontró el gasto"));   
    }
    
    public void eliminarGasto(Long id) {
        gastoRepository.deleteById(id);
    }
    
}
