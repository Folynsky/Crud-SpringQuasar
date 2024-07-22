package com.crud.practica.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "empleados")
public class empleadoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "empleadoid")
    private int empleadoId; 
    
    @Column(name = "nombre", nullable = false)
    private String nombre; 
    
    @Column(name = "telefono")
    private String telefono;
    
    @Column(name = "email", nullable = false)
    private String email;
    
    @Column(name = "puesto", nullable = false)
    private String puesto;

    public empleadoModel() {
    }

    public empleadoModel(int empleadoId, String nombre, String telefono, String email, String puesto) {
        this.empleadoId = empleadoId;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.puesto = puesto;
    }

    // Getters y Setters
    public int getEmpleadoId() {
        return empleadoId;
    }

    public void setEmpleadoId(int empleadoId) {
        this.empleadoId = empleadoId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    @Override
    public String toString() {
        return "EmpleadoModel{" +
                "empleadoId=" + empleadoId +
                ", nombre='" + nombre + '\'' +
                ", telefono='" + telefono + '\'' +
                ", email='" + email + '\'' +
                ", puesto='" + puesto + '\'' +
                '}';
    }
}
