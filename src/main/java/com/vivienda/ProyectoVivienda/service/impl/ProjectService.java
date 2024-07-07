package com.vivienda.ProyectoVivienda.service.impl;

import com.vivienda.ProyectoVivienda.model.ProjectModel;
import com.vivienda.ProyectoVivienda.repository.ProjectRepository;
import com.vivienda.ProyectoVivienda.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService implements IProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public ProjectModel createProject(ProjectModel project) {return projectRepository.save(project);    }

    @Override
    public ProjectModel updateProject(String id, ProjectModel project) {
        Optional<ProjectModel> existingProjectOptional = projectRepository.findById(id);

        if (existingProjectOptional.isPresent()) {
            ProjectModel existingProject = existingProjectOptional.get();
            existingProject.setName(project.getName());
            existingProject.setType(project.getType());

            return projectRepository.save(existingProject);
        } else {
            // Manejar el caso donde no se encuentra el proyecto
            return null; // o lanza una excepción según tu lógica de negocio
        }
    }

    @Override
    public List<ProjectModel> getProjects() {
        return projectRepository.findAll();
    }

    @Override
    public Optional<ProjectModel> getProjectById(String id) {
        return projectRepository.findById(id);
    }

    @Override
    public void deleteProject(String id) {
        projectRepository.deleteById(id);
    }
}
