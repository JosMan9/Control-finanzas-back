/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.control.finanzas.repository;

import com.control.finanzas.entity.Quincena;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Manuel
 */
@Repository
public interface QuincenaRepository extends JpaRepository<Quincena, Long>{
    
}
