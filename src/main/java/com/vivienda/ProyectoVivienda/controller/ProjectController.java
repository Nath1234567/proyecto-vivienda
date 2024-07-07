package com.vivienda.ProyectoVivienda.controller;

import com.vivienda.ProyectoVivienda.exception.CustomException;
import com.vivienda.ProyectoVivienda.model.ProjectModel;
import com.vivienda.ProyectoVivienda.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    @Autowired
    private IProjectService IProjectService;

    @PostMapping
    public ProjectModel createProject(@RequestBody ProjectModel project) {
        return IProjectService.createProject(project);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectModel> updateProject(@PathVariable String id, @RequestBody ProjectModel project) {
        ProjectModel updatedProject = IProjectService.updateProject(id, project);

        if (updatedProject != null) {
            return ResponseEntity.ok(updatedProject);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public List<ProjectModel> getProjects() {
        return IProjectService.getProjects();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectModel> getProjectById(@PathVariable("id") String id) {
        Optional<ProjectModel> project = IProjectService.getProjectById(id);
        if (project.isPresent()) {
            return new ResponseEntity<>(project.get(), HttpStatus.OK);
        } else {
            throw new CustomException("404", "No se encuentra el proyector");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable("id") String id) {
        IProjectService.deleteProject(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
