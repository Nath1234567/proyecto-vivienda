package com.vivienda.ProyectoVivienda.controller;

import com.vivienda.ProyectoVivienda.exception.CustomException;
import com.vivienda.ProyectoVivienda.model.ViviendaModel;
import com.vivienda.ProyectoVivienda.service.ViviendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/vivienda")
@Validated
public class ViviendaController {

    @Autowired
    private ViviendaService viviendaService;

    @PostMapping
    public ViviendaModel crearProyecto(@RequestBody ViviendaModel vivienda) {
        return viviendaService.crearVivienda(vivienda);
    }

    @GetMapping
    public List<ViviendaModel> obtenerProyectos() {
        return viviendaService.obtenerVivienda();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ViviendaModel> obtenerViviendaPorId(@PathVariable("id") String id) {
        Optional<ViviendaModel> vivienda = viviendaService.obtenerViviendaPorId(id);
        if (vivienda.isPresent()) {
            return new ResponseEntity<>(vivienda.get(), HttpStatus.OK);
        } else {
            throw new CustomException("404", "No se encuentra la vivienda");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") String id) {
        viviendaService.eliminarVivienda(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}