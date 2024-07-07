package com.vivienda.ProyectoVivienda.controller;

import com.vivienda.ProyectoVivienda.model.ProjectModel;
import com.vivienda.ProyectoVivienda.service.IProjectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ProjectControllerTest {

    private MockMvc mockMvc;

    @Mock
    private IProjectService IProjectService;

    @InjectMocks
    private ProjectController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void testCreateProject() throws Exception {
        String placeId = "1";
        ProjectModel project = new ProjectModel();
        project.setId(placeId);
        project.setName("Alameda");
        project.setType("Conjunto");

        when(IProjectService.createProject(any())).thenReturn(project);

        mockMvc.perform(post("/api/projects")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":\"1\",\"name\":\"Alameda\",\"type\":\"Conjunto\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Alameda"));

        verify(IProjectService, times(1)).createProject(any());
    }

    @Test
    void testUpdateProject() throws Exception {
        String projectId = "1";
        ProjectModel existingProject = new ProjectModel();
        existingProject.setId(projectId);
        existingProject.setName("ConjuntoPrueba");
        existingProject.setType("TipoPrueba");

        ProjectModel updatedProject = new ProjectModel();
        updatedProject.setId(projectId);
        updatedProject.setName("Updated");
        updatedProject.setType("Prueba");

        when(IProjectService.updateProject(eq(projectId), any(ProjectModel.class))).thenReturn(updatedProject);

        mockMvc.perform(put("/api/projects/{id}", projectId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Updated\",\"type\":\"Prueba\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated"));

        verify(IProjectService, times(1)).updateProject(eq(projectId), any(ProjectModel.class));
    }

    @Test
    void testGetProject() throws Exception {
        ProjectModel project = new ProjectModel();
        project.setId("1");
        project.setName("Conjunto");
        project.setType("Conjunto");

        when(IProjectService.getProjects()).thenReturn(Collections.singletonList(project));

        mockMvc.perform(get("/api/projects")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Conjunto"));
    }

    @Test
    void testGetById() throws Exception {
        String projectId = "1";
        ProjectModel project = new ProjectModel();
        project.setId(projectId);
        project.setName("Alameda");
        project.setType("Conjunto");

        when(IProjectService.getProjectById(projectId)).thenReturn(Optional.of(project));

        mockMvc.perform(get("/api/projects/{id}", projectId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Alameda"));
    }

    @Test
    void testDeleteProject() throws Exception {
        String projectId = "1";

        doNothing().when(IProjectService).deleteProject(projectId);

        mockMvc.perform(delete("/api/projects/{id}", projectId))
                .andExpect(status().isOk());

        verify(IProjectService, times(1)).deleteProject(projectId);
    }
}