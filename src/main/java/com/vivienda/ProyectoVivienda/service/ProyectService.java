package com.vivienda.ProyectoVivienda.service;

import com.vivienda.ProyectoVivienda.model.ProyectModel;
import com.vivienda.ProyectoVivienda.repository.ProyectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProyectService {

    @Autowired
    private ProyectRepository proyectoRepository;

    public ProyectModel crearProyecto(ProyectModel proyecto) {
        return proyectoRepository.save(proyecto);
    }

    public List<ProyectModel> obtenerProyectos() {
        return proyectoRepository.findAll();
    }

    public Optional<ProyectModel> obtenerProyectoPorId(String id) {
        return proyectoRepository.findById(id);
    }

    public void eliminarProyecto(String id) {
        proyectoRepository.deleteById(id);
    }
}
