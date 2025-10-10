/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.control.finanzas.repository;

import com.control.finanzas.entity.GastosTarjeta;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Manuel
 */
public interface GastosTarjetaRepository extends JpaRepository<GastosTarjeta, Long>{
    
}
