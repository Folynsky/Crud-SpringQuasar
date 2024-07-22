package com.crud.practica.controllers;

import com.crud.practica.models.entregasModel;
import com.crud.practica.repositories.entregasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/entregas")
public class entregasController {

    @Autowired
    private entregasRepository entregasRepository;

    @GetMapping
    public List<entregasModel> getAllEntregas() {
        return entregasRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<entregasModel> getEntregaById(@PathVariable int id) {
        Optional<entregasModel> entrega = entregasRepository.findById(id);
        if (entrega.isPresent()) {
            return ResponseEntity.ok(entrega.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public entregasModel createEntrega(@RequestBody entregasModel entrega) {
        return entregasRepository.save(entrega);
    }

    @PutMapping("/{id}")
    public ResponseEntity<entregasModel> updateEntrega(@PathVariable int id, @RequestBody entregasModel entregaDetails) {
        Optional<entregasModel> entrega = entregasRepository.findById(id);
        if (entrega.isPresent()) {
            entregasModel existingEntrega = entrega.get();
            existingEntrega.setPaqueteId(entregaDetails.getPaqueteId());
            existingEntrega.setEmpleadoId(entregaDetails.getEmpleadoId());
            existingEntrega.setRutaId(entregaDetails.getRutaId());
            existingEntrega.setFechaAsignacion(entregaDetails.getFechaAsignacion());
            existingEntrega.setFechaEntrega(entregaDetails.getFechaEntrega());
            existingEntrega.setEstado(entregaDetails.getEstado());
            entregasRepository.save(existingEntrega);
            return ResponseEntity.ok(existingEntrega);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEntrega(@PathVariable int id) {
        Optional<entregasModel> entrega = entregasRepository.findById(id);
        if (entrega.isPresent()) {
            entregasRepository.delete(entrega.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
