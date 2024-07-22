package com.crud.practica.repositories;

import com.crud.practica.models.empleadoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface empleadoRepository extends JpaRepository<empleadoModel, Integer> {
}
