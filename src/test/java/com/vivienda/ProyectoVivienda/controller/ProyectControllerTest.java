package com.vivienda.ProyectoVivienda.controller;

import com.vivienda.ProyectoVivienda.model.ProyectModel;
import com.vivienda.ProyectoVivienda.service.ProyectService;
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

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ProyectControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ProyectService proyectoService;

    @InjectMocks
    private ProyectController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void testCrearProyecto() throws Exception {
        ProyectModel proyecto = new ProyectModel();
        proyecto.setId("1");
        proyecto.setNombre("Alameda");
        proyecto.setTipo("Conjunto");

        when(proyectoService.crearProyecto(any())).thenReturn(proyecto);

        mockMvc.perform(post("/api/proyectos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":\"1\",\"nombre\":\"Alameda\",\"tipo\":\"Conjunto\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Alameda"));

        verify(proyectoService, times(1)).crearProyecto(any());
    }

    @Test
    void testObtenerProyectos() throws Exception {
        ProyectModel proyecto = new ProyectModel();
        proyecto.setId("1");
        proyecto.setNombre("Conjunto");
        proyecto.setTipo("Conjunto");

        when(proyectoService.obtenerProyectos()).thenReturn(Collections.singletonList(proyecto));

        mockMvc.perform(get("/api/proyectos")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Conjunto"));
    }

    @Test
    void testObtenerProyectoPorId() throws Exception {
        String proyectoId = "1";
        ProyectModel proyecto = new ProyectModel();
        proyecto.setId(proyectoId);
        proyecto.setNombre("Alameda");
        proyecto.setTipo("Conjunto");

        when(proyectoService.obtenerProyectoPorId(proyectoId)).thenReturn(Optional.of(proyecto));

        mockMvc.perform(get("/api/proyectos/{id}", proyectoId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Alameda"));
    }

    @Test
    void testEliminarProyecto() throws Exception {
        String proyectoId = "1";

        doNothing().when(proyectoService).eliminarProyecto(proyectoId);

        mockMvc.perform(delete("/api/proyectos/{id}", proyectoId))
                .andExpect(status().isNoContent());

        verify(proyectoService, times(1)).eliminarProyecto(proyectoId);
    }
}