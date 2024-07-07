package com.vivienda.ProyectoVivienda.controller;

import com.vivienda.ProyectoVivienda.model.PlaceModel;
import com.vivienda.ProyectoVivienda.service.IPlaceService;
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


class PlaceControllerTest {

    private MockMvc mockMvc;

    @Mock
    private IPlaceService IPlaceService;

    @InjectMocks
    private PlaceController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void testCreatePlace() throws Exception {
        PlaceModel place = new PlaceModel();
        place.setId("1");
        place.setType("Apartamento");
        place.setPrice(100000f);
        place.setState("Disponible");

        when(IPlaceService.createPlace(any(PlaceModel.class))).thenReturn(place);

        mockMvc.perform(post("/api/place")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"type\":\"Apartamento\", \"price\":100000, \"state\":\"Disponible\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.type").value("Apartamento"))
                .andExpect(jsonPath("$.price").value(100000f))
                .andExpect(jsonPath("$.state").value("Disponible"));

        verify(IPlaceService, times(1)).createPlace(any(PlaceModel.class));
    }

    @Test
    void testUpdatePlace() throws Exception {
        String placeId = "1";
        PlaceModel existingPlace = new PlaceModel();
        existingPlace.setId(placeId);
        existingPlace.setState("StatePrueba");
        existingPlace.setType("TypePrueba");

        PlaceModel updatedPlace = new PlaceModel();
        updatedPlace.setId(placeId);
        updatedPlace.setState("Updated");
        updatedPlace.setType("Prueba");

        when(IPlaceService.updatePlace(eq(placeId), any(PlaceModel.class))).thenReturn(updatedPlace);

        mockMvc.perform(put("/api/place/{id}", placeId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"state\":\"Updated\",\"type\":\"Updated\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.state").value("Updated"));

        verify(IPlaceService, times(1)).updatePlace(eq(placeId), any(PlaceModel.class));
    }

    @Test
    void testGetPlace() throws Exception {
        String placeId = "1";
        PlaceModel place = new PlaceModel();
        place.setId(placeId);
        place.setType("Apartamento");
        place.setPrice(100000f);
        place.setState("Disponible");

        when(IPlaceService.getPlace()).thenReturn(Collections.singletonList(place));

        mockMvc.perform(get("/api/place"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("1"))
                .andExpect(jsonPath("$[0].type").value("Apartamento"))
                .andExpect(jsonPath("$[0].price").value(100000f))
                .andExpect(jsonPath("$[0].state").value("Disponible"));

        verify(IPlaceService, times(1)).getPlace();
    }

    @Test
    void testGetById() throws Exception {
        String placeId = "1";
        PlaceModel place = new PlaceModel();
        place.setId(placeId);
        place.setType("Apartamento");
        place.setPrice(100000f);
        place.setState("Disponible");

        when(IPlaceService.getPlaceById(placeId)).thenReturn(Optional.of(place));

        mockMvc.perform(get("/api/place/{id}", placeId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.type").value("Apartamento"))
                .andExpect(jsonPath("$.price").value(100000f))
                .andExpect(jsonPath("$.state").value("Disponible"));

        verify(IPlaceService, times(1)).getPlaceById(placeId);
    }

    @Test
    void testDeletePlace() throws Exception {
        doNothing().when(IPlaceService).deletePlace("1");

        mockMvc.perform(delete("/api/place/1"))
                .andExpect(status().isOk());

        verify(IPlaceService, times(1)).deletePlace("1");
    }
}
