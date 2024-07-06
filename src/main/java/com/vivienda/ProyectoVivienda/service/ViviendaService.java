package com.vivienda.ProyectoVivienda.service;

import com.vivienda.ProyectoVivienda.model.ViviendaModel;
import com.vivienda.ProyectoVivienda.repository.ViviendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ViviendaService {
    @Autowired
    private ViviendaRepository viviendaRepository;

    public ViviendaModel crearVivienda(ViviendaModel vivienda) {
        return viviendaRepository.save(vivienda);
    }

    public List<ViviendaModel> obtenerVivienda() {
        return viviendaRepository.findAll();
    }

    public Optional<ViviendaModel> obtenerViviendaPorId(String id) {
        return viviendaRepository.findById(id);
    }

    public void eliminarVivienda(String id) {
        viviendaRepository.deleteById(id);
    }
}
