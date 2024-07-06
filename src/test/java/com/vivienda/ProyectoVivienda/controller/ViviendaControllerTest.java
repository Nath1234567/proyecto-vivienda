package com.vivienda.ProyectoVivienda.controller;

import com.vivienda.ProyectoVivienda.model.ViviendaModel;
import com.vivienda.ProyectoVivienda.service.ViviendaService;
import org.junit.jupiter.api.*;
import org.mockito.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


class ViviendaControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ViviendaService viviendaService;

    @InjectMocks
    private ViviendaController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void testCrearVivienda() throws Exception {
        ViviendaModel vivienda = new ViviendaModel();
        vivienda.setId("1");
        vivienda.setTipo("Apartamento");
        vivienda.setPrecio(100000f);
        vivienda.setEstadoVenta("Disponible");

        when(viviendaService.crearVivienda(any(ViviendaModel.class))).thenReturn(vivienda);

        mockMvc.perform(post("/api/vivienda")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"tipo\":\"Apartamento\", \"precio\":100000, \"estadoVenta\":\"Disponible\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.tipo").value("Apartamento"))
                .andExpect(jsonPath("$.precio").value(100000f))
                .andExpect(jsonPath("$.estadoVenta").value("Disponible"));

        verify(viviendaService, times(1)).crearVivienda(any(ViviendaModel.class));
    }

    @Test
    void testObtenerVivienda() throws Exception {
        ViviendaModel vivienda = new ViviendaModel();
        vivienda.setId("1");
        vivienda.setTipo("Apartamento");
        vivienda.setPrecio(100000f);
        vivienda.setEstadoVenta("Disponible");

        when(viviendaService.obtenerVivienda()).thenReturn(Collections.singletonList(vivienda));

        mockMvc.perform(get("/api/vivienda"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("1"))
                .andExpect(jsonPath("$[0].tipo").value("Apartamento"))
                .andExpect(jsonPath("$[0].precio").value(100000f))
                .andExpect(jsonPath("$[0].estadoVenta").value("Disponible"));

        verify(viviendaService, times(1)).obtenerVivienda();
    }

    @Test
    void testObtenerViviendaPorId() throws Exception {
        ViviendaModel vivienda = new ViviendaModel();
        vivienda.setId("1");
        vivienda.setTipo("Apartamento");
        vivienda.setPrecio(100000f);
        vivienda.setEstadoVenta("Disponible");

        when(viviendaService.obtenerViviendaPorId("1")).thenReturn(Optional.of(vivienda));

        mockMvc.perform(get("/api/vivienda/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.tipo").value("Apartamento"))
                .andExpect(jsonPath("$.precio").value(100000f))
                .andExpect(jsonPath("$.estadoVenta").value("Disponible"));

        verify(viviendaService, times(1)).obtenerViviendaPorId("1");
    }

    @Test
    void testEliminarVivienda() throws Exception {
        doNothing().when(viviendaService).eliminarVivienda("1");

        mockMvc.perform(delete("/api/vivienda/1"))
                .andExpect(status().isNoContent());

        verify(viviendaService, times(1)).eliminarVivienda("1");
    }
}
