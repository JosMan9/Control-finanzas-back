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
@Table(name = "gasto")
@Data
@Getter
@Setter
@NoArgsConstructor
public class Gasto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String concepto;
    
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal monto;
    
    @Column(nullable = true, name = "fecha_operacion")
    private Date fechaOperacion;
    
    @Column(nullable = false, name = "fecha_quincena")
    private Date fechaQuincena;
    
    @Column(nullable = false, name = "es_cubierto")
    private Boolean esCubierto;
    
    @OneToOne
    @JoinColumn(name = "ingreso_id")
    private Ingreso ingreso;
    
    @OneToOne
    @JoinColumn(name = "tipo_gasto_id")
    private TipoGasto tipoGasto;
}
