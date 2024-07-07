package com.vivienda.ProyectoVivienda.service;

import com.vivienda.ProyectoVivienda.model.ProjectModel;

import java.util.List;
import java.util.Optional;

public interface IProjectService {
    ProjectModel createProject(ProjectModel project);

    ProjectModel updateProject(String id, ProjectModel project);

    List<ProjectModel> getProjects();

    Optional<ProjectModel> getProjectById(String id);

    void deleteProject(String id);
}
