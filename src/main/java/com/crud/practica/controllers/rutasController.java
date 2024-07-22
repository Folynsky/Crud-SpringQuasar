package com.crud.practica.controllers;

import com.crud.practica.models.rutasModel;
import com.crud.practica.repositories.rutasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/rutas")
public class rutasController {

    @Autowired
    private rutasRepository rutasRepository;

    @GetMapping
    public List<rutasModel> getAllRutas() {
        return rutasRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<rutasModel> getRutaById(@PathVariable int id) {
        Optional<rutasModel> ruta = rutasRepository.findById(id);
        if (ruta.isPresent()) {
            return ResponseEntity.ok(ruta.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public rutasModel createRuta(@RequestBody rutasModel ruta) {
        return rutasRepository.save(ruta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<rutasModel> updateRuta(@PathVariable int id, @RequestBody rutasModel rutaDetails) {
        Optional<rutasModel> ruta = rutasRepository.findById(id);
        if (ruta.isPresent()) {
            rutasModel existingRuta = ruta.get();
            existingRuta.setDescripcion(rutaDetails.getDescripcion());
            existingRuta.setDistancia(rutaDetails.getDistancia());
            existingRuta.setTiempoEstimado(rutaDetails.getTiempoEstimado());
            rutasRepository.save(existingRuta);
            return ResponseEntity.ok(existingRuta);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRuta(@PathVariable int id) {
        Optional<rutasModel> ruta = rutasRepository.findById(id);
        if (ruta.isPresent()) {
            rutasRepository.delete(ruta.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
