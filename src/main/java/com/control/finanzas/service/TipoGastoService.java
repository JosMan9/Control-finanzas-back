/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.control.finanzas.service;

import com.control.finanzas.entity.TipoGasto;
import com.control.finanzas.repository.TipoGastoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Manuel
 */
@Service
public class TipoGastoService {
    
    @Autowired
    private TipoGastoRepository gastoRepository;
    
    public TipoGasto agregarTipoGasto(TipoGasto gasto) {
        return gastoRepository.save(gasto);
    }
    
    public List<TipoGasto> obtenerTodosTipoGasto() {
        return gastoRepository.findAll();
    }
    
    public TipoGasto obtenerTipoGastoById(Long id) {
        return gastoRepository.findById(id).orElseThrow(() -> new RuntimeException("No se encontró el tipo de gasto"));
    }
    
   public TipoGasto actualizarTipoGasto(Long id, TipoGasto gasto) {
       return gastoRepository.findById(id).map(tipoGasto -> {
           tipoGasto.setNombre(gasto.getNombre());
           return gastoRepository.save(tipoGasto);
       }).orElseThrow(() -> new RuntimeException("No se encontró el tipo de gasto"));
   }
   
   public void eliminarTipoGsto(Long id) {
       gastoRepository.deleteById(id);
   }
    
    
}
