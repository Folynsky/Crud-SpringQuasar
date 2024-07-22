package com.crud.practica.repositories;

import com.crud.practica.models.paquetesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface paquetesRepository extends JpaRepository<paquetesModel, Integer> {
}
