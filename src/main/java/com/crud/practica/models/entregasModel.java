package com.crud.practica.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.sql.Date;

@Entity
@Table(name = "entregas")
public class entregasModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "entregaid")
    private int entregaId; 
    
    @Column(name = "paqueteid", nullable = false)
    private int paqueteId; 
    
    @Column(name = "empleadoid", nullable = false)
    private int empleadoId;
    
    @Column(name = "rutaid", nullable = false)
    private int rutaId; 
    
    @Column(name = "fechaasignacion", nullable = false)
    private Date fechaAsignacion; 
    
    @Column(name = "fechaentrega")
    private Date fechaEntrega;
    
    @Column(name = "estado", nullable = false)
    private String estado;

    public entregasModel() {
    }

    public entregasModel(int entregaId, int paqueteId, int empleadoId, int rutaId, Date fechaAsignacion, Date fechaEntrega, String estado) {
        this.entregaId = entregaId;
        this.paqueteId = paqueteId;
        this.empleadoId = empleadoId;
        this.rutaId = rutaId;
        this.fechaAsignacion = fechaAsignacion;
        this.fechaEntrega = fechaEntrega;
        this.estado = estado;
    }

    // Getters y Setters
    public int getEntregaId() {
        return entregaId;
    }

    public void setEntregaId(int entregaId) {
        this.entregaId = entregaId;
    }

    public int getPaqueteId() {
        return paqueteId;
    }

    public void setPaqueteId(int paqueteId) {
        this.paqueteId = paqueteId;
    }

    public int getEmpleadoId() {
        return empleadoId;
    }

    public void setEmpleadoId(int empleadoId) {
        this.empleadoId = empleadoId;
    }

    public int getRutaId() {
        return rutaId;
    }

    public void setRutaId(int rutaId) {
        this.rutaId = rutaId;
    }

    public Date getFechaAsignacion() {
        return fechaAsignacion;
    }

    public void setFechaAsignacion(Date fechaAsignacion) {
        this.fechaAsignacion = fechaAsignacion;
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
        return "EntregasModel{" +
                "entregaId=" + entregaId +
                ", paqueteId=" + paqueteId +
                ", empleadoId=" + empleadoId +
                ", rutaId=" + rutaId +
                ", fechaAsignacion=" + fechaAsignacion +
                ", fechaEntrega=" + fechaEntrega +
                ", estado='" + estado + '\'' +
                '}';
    }
}
