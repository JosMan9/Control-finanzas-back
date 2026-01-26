/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.control.finanzas.service;

import com.control.finanzas.entity.Gasto;
import com.control.finanzas.entity.GastosTarjeta;
import com.control.finanzas.entity.Mes;
import com.control.finanzas.entity.Tarjeta;
import com.control.finanzas.repository.GastoRepository;
import com.control.finanzas.repository.GastosTarjetaRepository;
import com.control.finanzas.repository.MesRepository;
import com.control.finanzas.repository.TarjetaRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Manuel
 */
@Service
public class GastoTarjetaService {
    
    @Autowired
    private GastosTarjetaRepository gastoTarjetaRepository;
    
    @Autowired
    private TarjetaRepository tarjetaRepository;
    
    @Autowired
    private GastoRepository gastoRepository;
    
    @Autowired
    private MesRepository mesRepository;
    
    public GastosTarjeta agregarGastoTarjeta(GastosTarjeta gt) {
        Gasto gasto = gastoRepository.findById(gt.getGasto().getId())
                .orElseThrow(() -> new RuntimeException("No se encontró el gasto"));
        Tarjeta tarjeta = tarjetaRepository.findById(gt.getTarjeta().getId())
                .orElseThrow(() -> new RuntimeException("No se encontró la tarjeta"));
        Mes mes = mesRepository.findById(gt.getMes().getId())
                .orElseThrow(() -> new RuntimeException("No se encontró el mes"));
        gt.setTarjeta(tarjeta);
        gt.setGasto(gasto);
        gt.setMes(mes);
        return gastoTarjetaRepository.save(gt);
    }
    
    public List<GastosTarjeta> obtenerTodos() {
        return gastoTarjetaRepository.findAll();
    }
    
    public Optional<GastosTarjeta> obtenerGastoTarjets(Long id) {
        return gastoTarjetaRepository.findById(id);
    }
    
    public Optional<GastosTarjeta> actualizarGastoTarjeta(Long id, GastosTarjeta gastosTarjeta) {
        return gastoTarjetaRepository.findById(id).map(gt -> {
            gt.setCantidadAbonada(gastosTarjeta.getCantidadAbonada());
            gt.setEsMio(gastosTarjeta.getEsMio());
            gt.setGasto(gastosTarjeta.getGasto());
            gt.setMesActual(gastosTarjeta.getMesActual());
            gt.setMesFinal(gastosTarjeta.getMesFinal());
            gt.setTarjeta(gastosTarjeta.getTarjeta());
            gt.setMes(gastosTarjeta.getMes());
            return gastoTarjetaRepository.save(gt);
        });
    }
    
    public boolean eliminarGastosTarjeta(Long id) {
        if(gastoTarjetaRepository.existsById(id)) {
            gastoTarjetaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
