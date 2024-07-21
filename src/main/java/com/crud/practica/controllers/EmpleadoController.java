package com.crud.practica.controllers;

import com.crud.practica.models.EmpleadoModel;
import com.crud.practica.repositories.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/empleados")
public class EmpleadoController {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @GetMapping
    public List<EmpleadoModel> getAllEmpleados() {
        return empleadoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpleadoModel> getEmpleadoById(@PathVariable int id) {
        Optional<EmpleadoModel> empleado = empleadoRepository.findById(id);
        if (empleado.isPresent()) {
            return ResponseEntity.ok(empleado.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public EmpleadoModel createEmpleado(@RequestBody EmpleadoModel empleado) {
        return empleadoRepository.save(empleado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmpleadoModel> updateEmpleado(@PathVariable int id, @RequestBody EmpleadoModel empleadoDetails) {
        Optional<EmpleadoModel> empleado = empleadoRepository.findById(id);
        if (empleado.isPresent()) {
            EmpleadoModel existingEmpleado = empleado.get();
            existingEmpleado.setNombre(empleadoDetails.getNombre());
            existingEmpleado.setTelefono(empleadoDetails.getTelefono());
            existingEmpleado.setEmail(empleadoDetails.getEmail());
            existingEmpleado.setPuesto(empleadoDetails.getPuesto());
            empleadoRepository.save(existingEmpleado);
            return ResponseEntity.ok(existingEmpleado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmpleado(@PathVariable int id) {
        Optional<EmpleadoModel> empleado = empleadoRepository.findById(id);
        if (empleado.isPresent()) {
            empleadoRepository.delete(empleado.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
