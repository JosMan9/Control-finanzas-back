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
@Table(name = "prestamo_deuda")
@Data
@Getter
@Setter
@NoArgsConstructor
public class PrestamoDeuda {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "persona_id")
    private Persona persona;
    
    @Column(nullable = false, name = "cantidad_prestada")
    private BigDecimal cantidadPrestada;
    
    @Column(nullable = false, name = "cantidad_acumulada")
    private BigDecimal cantidadAcumulada;
    
    @Column(nullable = false, name = "fecha_prestamo")
    private Date fechaPrestamo;
    
    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;
    
    
    public void pagar(BigDecimal montoPago) {
        if(montoPago == null ||  montoPago.compareTo(BigDecimal.ZERO) <= 0) {
           throw new IllegalArgumentException("El pago debe ser mayor a 0");
        }
        
        this.cantidadAcumulada = this.cantidadAcumulada.add(montoPago);
        
        if(this.cantidadAcumulada.compareTo(this.cantidadPrestada) > 0) {
            throw new IllegalStateException("El pago excede la deuda");
        }
    
    }
    
}
