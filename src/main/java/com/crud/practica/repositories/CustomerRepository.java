package com.crud.practica.repositories;

import com.crud.practica.models.customerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface customerRepository extends JpaRepository<customerModel, Integer> {
}
