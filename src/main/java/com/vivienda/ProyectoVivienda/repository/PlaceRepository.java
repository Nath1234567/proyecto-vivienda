package com.vivienda.ProyectoVivienda.repository;

import com.vivienda.ProyectoVivienda.model.PlaceModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaceRepository extends MongoRepository<PlaceModel, String> {
}
