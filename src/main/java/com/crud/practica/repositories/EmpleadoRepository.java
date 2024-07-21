package com.crud.practica.repositories;

import com.crud.practica.models.EmpleadoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpleadoRepository extends JpaRepository<EmpleadoModel, Integer> {
}
