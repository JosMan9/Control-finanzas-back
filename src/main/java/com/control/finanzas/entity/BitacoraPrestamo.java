/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.control.finanzas.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Manuel
 */
@Entity
@Table(name = "bitacora_prestamo")
@Data
@Getter
@Setter
@NoArgsConstructor
public class BitacoraPrestamo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "quincena_id")
    private Quincena quincena;
    
    @Column(name = "cantidad_abonada")
    private BigDecimal cantidadAbonada;
    
    @ManyToOne
    @JoinColumn(name = "prestamo_deuda_id")
    private PrestamoDeuda prestamoDeuda;
    
    @Column(name = "fecha_accion")
    private Date fechaAccion;
    
    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;
}
