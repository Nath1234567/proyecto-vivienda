package com.vivienda.ProyectoVivienda.repository;

import com.vivienda.ProyectoVivienda.model.ProjectModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends MongoRepository<ProjectModel, String> {

}
