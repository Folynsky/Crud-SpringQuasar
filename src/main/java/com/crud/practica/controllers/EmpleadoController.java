package com.crud.practica.controllers;

import com.crud.practica.models.empleadoModel;
import com.crud.practica.repositories.empleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/empleados")
public class empleadoController {

    @Autowired
    private empleadoRepository empleadoRepository;

    @GetMapping
    public List<empleadoModel> getAllEmpleados() {
        return empleadoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<empleadoModel> getEmpleadoById(@PathVariable int id) {
        Optional<empleadoModel> empleado = empleadoRepository.findById(id);
        if (empleado.isPresent()) {
            return ResponseEntity.ok(empleado.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public empleadoModel createEmpleado(@RequestBody empleadoModel empleado) {
        return empleadoRepository.save(empleado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<empleadoModel> updateEmpleado(@PathVariable int id, @RequestBody empleadoModel empleadoDetails) {
        Optional<empleadoModel> empleado = empleadoRepository.findById(id);
        if (empleado.isPresent()) {
            empleadoModel existingEmpleado = empleado.get();
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
        Optional<empleadoModel> empleado = empleadoRepository.findById(id);
        if (empleado.isPresent()) {
            empleadoRepository.delete(empleado.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
