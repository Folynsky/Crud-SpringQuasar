package com.crud.practica.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "rutas")
public class rutasModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rutaid")
    private int rutaId; 
    
    @Column(name = "descripcion", nullable = false)
    private String descripcion; 
    
    @Column(name = "distancia", nullable = false)
    private BigDecimal distancia;
    
    @Column(name = "tiempoestimado", nullable = false)
    private BigDecimal tiempoEstimado;

    public rutasModel() {
    }

    public rutasModel(int rutaId, String descripcion, BigDecimal distancia, BigDecimal tiempoEstimado) {
        this.rutaId = rutaId;
        this.descripcion = descripcion;
        this.distancia = distancia;
        this.tiempoEstimado = tiempoEstimado;
    }

    // Getters y Setters
    public int getRutaId() {
        return rutaId;
    }

    public void setRutaId(int rutaId) {
        this.rutaId = rutaId;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getDistancia() {
        return distancia;
    }

    public void setDistancia(BigDecimal distancia) {
        this.distancia = distancia;
    }

    public BigDecimal getTiempoEstimado() {
        return tiempoEstimado;
    }

    public void setTiempoEstimado(BigDecimal tiempoEstimado) {
        this.tiempoEstimado = tiempoEstimado;
    }

    @Override
    public String toString() {
        return "RutasModel{" +
                "rutaId=" + rutaId +
                ", descripcion='" + descripcion + '\'' +
                ", distancia=" + distancia +
                ", tiempoEstimado=" + tiempoEstimado +
                '}';
    }
}
