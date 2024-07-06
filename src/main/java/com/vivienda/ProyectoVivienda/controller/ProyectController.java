package com.vivienda.ProyectoVivienda.controller;

import com.vivienda.ProyectoVivienda.exception.CustomException;
import com.vivienda.ProyectoVivienda.model.ProyectModel;
import com.vivienda.ProyectoVivienda.service.ProyectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api/proyectos")
public class ProyectController {

    @Autowired
    private ProyectService proyectoService;

    @PostMapping
    public ProyectModel crearProyecto(@RequestBody ProyectModel proyecto) {
        return proyectoService.crearProyecto(proyecto);
    }

    @GetMapping
    public List<ProyectModel> obtenerProyectos() {
        return proyectoService.obtenerProyectos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProyectModel> obtenerViviendaPorId(@PathVariable("id") String id) {
        Optional<ProyectModel> proyecto = proyectoService.obtenerProyectoPorId(id);
        if (proyecto.isPresent()) {
            return new ResponseEntity<>(proyecto.get(), HttpStatus.OK);
        } else {
            throw new CustomException("404", "No se encuentra el proyector");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") String id) {
        proyectoService.eliminarProyecto(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
