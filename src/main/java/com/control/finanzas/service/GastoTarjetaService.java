/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.control.finanzas.service;

import com.control.finanzas.entity.Gasto;
import com.control.finanzas.repository.GastoRepository;
import com.control.finanzas.repository.GastosTarjetaRepository;
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
    private GastoRepository gastoRepository;
    
}
