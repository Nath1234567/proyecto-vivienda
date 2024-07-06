package com.vivienda.ProyectoVivienda.repository;

import com.vivienda.ProyectoVivienda.model.ViviendaModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ViviendaRepository extends MongoRepository<ViviendaModel, String> {
}
