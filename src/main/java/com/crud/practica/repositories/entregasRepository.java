package com.crud.practica.repositories;

import com.crud.practica.models.entregasModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface entregasRepository extends JpaRepository<entregasModel, Integer> {
}
