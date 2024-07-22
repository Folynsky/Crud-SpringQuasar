package com.crud.practica.controllers;

import com.crud.practica.models.eventosModel;
import com.crud.practica.repositories.eventosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/eventos")
public class eventosController {

    @Autowired
    private eventosRepository eventosRepository;

    @GetMapping
    public List<eventosModel> getAllEventos() {
        return eventosRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<eventosModel> getEventoById(@PathVariable int id) {
        Optional<eventosModel> evento = eventosRepository.findById(id);
        if (evento.isPresent()) {
            return ResponseEntity.ok(evento.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public eventosModel createEvento(@RequestBody eventosModel evento) {
        return eventosRepository.save(evento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<eventosModel> updateEvento(@PathVariable int id, @RequestBody eventosModel eventoDetails) {
        Optional<eventosModel> evento = eventosRepository.findById(id);
        if (evento.isPresent()) {
            eventosModel existingEvento = evento.get();
            existingEvento.setEntregaId(eventoDetails.getEntregaId());
            existingEvento.setFechaEvento(eventoDetails.getFechaEvento());
            existingEvento.setDescripcion(eventoDetails.getDescripcion());
            eventosRepository.save(existingEvento);
            return ResponseEntity.ok(existingEvento);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvento(@PathVariable int id) {
        Optional<eventosModel> evento = eventosRepository.findById(id);
        if (evento.isPresent()) {
            eventosRepository.delete(evento.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
