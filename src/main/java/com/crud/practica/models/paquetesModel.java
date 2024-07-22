package com.crud.practica.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Table(name = "paquetes")
public class paquetesModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "paqueteid")
    private int paqueteId; 
    
    @Column(name = "clienteid", nullable = false)
    private int clienteId; 
    
    @Column(name = "descripcion", nullable = false)
    private String descripcion;
    
    @Column(name = "peso", nullable = false)
    private BigDecimal peso; 
    
    @Column(name = "dimensiones", nullable = false)
    private String dimensiones; 
    
    @Column(name = "fechaenvio", nullable = false)
    private Date fechaEnvio;
    
    @Column(name = "fechaentrega")
    private Date fechaEntrega;
    
    @Column(name = "estado", nullable = false)
    private String estado;

    public paquetesModel() {
    }

    public paquetesModel(int paqueteId, int clienteId, String descripcion, BigDecimal peso, String dimensiones, Date fechaEnvio, Date fechaEntrega, String estado) {
        this.paqueteId = paqueteId;
        this.clienteId = clienteId;
        this.descripcion = descripcion;
        this.peso = peso;
        this.dimensiones = dimensiones;
        this.fechaEnvio = fechaEnvio;
        this.fechaEntrega = fechaEntrega;
        this.estado = estado;
    }

    // Getters y Setters
    public int getPaqueteId() {
        return paqueteId;
    }

    public void setPaqueteId(int paqueteId) {
        this.paqueteId = paqueteId;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getPeso() {
        return peso;
    }

    public void setPeso(BigDecimal peso) {
        this.peso = peso;
    }

    public String getDimensiones() {
        return dimensiones;
    }

    public void setDimensiones(String dimensiones) {
        this.dimensiones = dimensiones;
    }

    public Date getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(Date fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "PaquetesModel{" +
                "paqueteId=" + paqueteId +
                ", clienteId=" + clienteId +
                ", descripcion='" + descripcion + '\'' +
                ", peso=" + peso +
                ", dimensiones='" + dimensiones + '\'' +
                ", fechaEnvio=" + fechaEnvio +
                ", fechaEntrega=" + fechaEntrega +
                ", estado='" + estado + '\'' +
                '}';
    }
}
