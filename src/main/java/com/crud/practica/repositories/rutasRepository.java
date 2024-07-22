package com.crud.practica.repositories;

import com.crud.practica.models.rutasModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface rutasRepository extends JpaRepository<rutasModel, Integer> {
}
