package com.vivienda.ProyectoVivienda.repository;

import com.vivienda.ProyectoVivienda.model.ProyectModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProyectRepository extends MongoRepository<ProyectModel, String> {

}
