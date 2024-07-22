package com.crud.practica.controllers;

import com.crud.practica.models.customerModel;
import com.crud.practica.repositories.customerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customers")
public class customerController {

    @Autowired
    private customerRepository customerRepository;

    @GetMapping
    public List<customerModel> getAllCustomers() {
        return customerRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<customerModel> getCustomerById(@PathVariable int id) {
        Optional<customerModel> customer = customerRepository.findById(id);
        if (customer.isPresent()) {
            return ResponseEntity.ok(customer.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public customerModel createCustomer(@RequestBody customerModel customer) {
        return customerRepository.save(customer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<customerModel> updateCustomer(@PathVariable int id, @RequestBody customerModel customerDetails) {
        Optional<customerModel> customer = customerRepository.findById(id);
        if (customer.isPresent()) {
            customerModel existingCustomer = customer.get();
            existingCustomer.setNombre(customerDetails.getNombre());
            existingCustomer.setDireccion(customerDetails.getDireccion());
            existingCustomer.setCiudad(customerDetails.getCiudad());
            existingCustomer.setEstado(customerDetails.getEstado());
            existingCustomer.setCodigoPostal(customerDetails.getCodigoPostal());
            existingCustomer.setTelefono(customerDetails.getTelefono());
            existingCustomer.setEmail(customerDetails.getEmail());
            customerRepository.save(existingCustomer);
            return ResponseEntity.ok(existingCustomer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable int id) {
        Optional<customerModel> customer = customerRepository.findById(id);
        if (customer.isPresent()) {
            customerRepository.delete(customer.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
