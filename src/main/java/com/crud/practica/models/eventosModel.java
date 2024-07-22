package com.crud.practica.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "eventos")
public class eventosModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "eventoid")
    private int eventoId; 
    
    @Column(name = "entregaid", nullable = false)
    private int entregaId; 
    
    @Column(name = "fechaevento", nullable = false)
    private Timestamp fechaEvento;
    
    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    public eventosModel() {
    }

    public eventosModel(int eventoId, int entregaId, Timestamp fechaEvento, String descripcion) {
        this.eventoId = eventoId;
        this.entregaId = entregaId;
        this.fechaEvento = fechaEvento;
        this.descripcion = descripcion;
    }

    // Getters y Setters
    public int getEventoId() {
        return eventoId;
    }

    public void setEventoId(int eventoId) {
        this.eventoId = eventoId;
    }

    public int getEntregaId() {
        return entregaId;
    }

    public void setEntregaId(int entregaId) {
        this.entregaId = entregaId;
    }

    public Timestamp getFechaEvento() {
        return fechaEvento;
    }

    public void setFechaEvento(Timestamp fechaEvento) {
        this.fechaEvento = fechaEvento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "EventosModel{" +
                "eventoId=" + eventoId +
                ", entregaId=" + entregaId +
                ", fechaEvento=" + fechaEvento +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
