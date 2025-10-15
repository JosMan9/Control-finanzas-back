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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Manuel
 */
@Entity
@Table(name = "gastos_tarjeta")
@Getter
@Setter
@NoArgsConstructor
public class GastosTarjeta {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Min(1)
    @Max(12)
    @Column(name = "mes_actual", nullable = false)
    private Integer mesActual;
    
    @Min(1)
    @Max(12)
    @Column(name = "mes_final", nullable = false)
    private Integer mesFinal;
    
    @OneToOne
    @JoinColumn(unique = true, name = "tarjeta_id")
    private Tarjeta tarjeta;
    
    @Column(name = "es_mio", nullable = false)
    private Boolean eMio;
    
    @Column(name = "cantidad_abonada", nullable = false, precision = 10, scale = 2)
    private BigDecimal cantidadAbonada;
    
    @OneToOne
    @JoinColumn(name = "gasto_id", unique = true)
    private Gasto gasto;
}
