package com.crud.practica.controllers;

import com.crud.practica.models.paquetesModel;
import com.crud.practica.repositories.paquetesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/paquetes")
public class paquetesController {

    @Autowired
    private paquetesRepository paquetesRepository;

    @GetMapping
    public List<paquetesModel> getAllPaquetes() {
        return paquetesRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<paquetesModel> getPaqueteById(@PathVariable int id) {
        Optional<paquetesModel> paquete = paquetesRepository.findById(id);
        if (paquete.isPresent()) {
            return ResponseEntity.ok(paquete.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public paquetesModel createPaquete(@RequestBody paquetesModel paquete) {
        return paquetesRepository.save(paquete);
    }

    @PutMapping("/{id}")
    public ResponseEntity<paquetesModel> updatePaquete(@PathVariable int id, @RequestBody paquetesModel paqueteDetails) {
        Optional<paquetesModel> paquete = paquetesRepository.findById(id);
        if (paquete.isPresent()) {
            paquetesModel existingPaquete = paquete.get();
            existingPaquete.setClienteId(paqueteDetails.getClienteId());
            existingPaquete.setDescripcion(paqueteDetails.getDescripcion());
            existingPaquete.setPeso(paqueteDetails.getPeso());
            existingPaquete.setDimensiones(paqueteDetails.getDimensiones());
            existingPaquete.setFechaEnvio(paqueteDetails.getFechaEnvio());
            existingPaquete.setFechaEntrega(paqueteDetails.getFechaEntrega());
            existingPaquete.setEstado(paqueteDetails.getEstado());
            paquetesRepository.save(existingPaquete);
            return ResponseEntity.ok(existingPaquete);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePaquete(@PathVariable int id) {
        Optional<paquetesModel> paquete = paquetesRepository.findById(id);
        if (paquete.isPresent()) {
            paquetesRepository.delete(paquete.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}