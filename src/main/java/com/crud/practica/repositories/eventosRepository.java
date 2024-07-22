package com.crud.practica.repositories;

import com.crud.practica.models.eventosModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface eventosRepository extends JpaRepository<eventosModel, Integer> {
}
