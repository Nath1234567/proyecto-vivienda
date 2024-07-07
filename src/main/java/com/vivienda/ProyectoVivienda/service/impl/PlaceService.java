package com.vivienda.ProyectoVivienda.service.impl;

import com.vivienda.ProyectoVivienda.model.PlaceModel;
import com.vivienda.ProyectoVivienda.repository.PlaceRepository;
import com.vivienda.ProyectoVivienda.service.IPlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlaceService implements IPlaceService {
    @Autowired
    private PlaceRepository placeRepository;

    @Override
    public PlaceModel createPlace(PlaceModel placeModel) {return placeRepository.save(placeModel);   }

    @Override
    public PlaceModel updatePlace(String id, PlaceModel place) {
        Optional<PlaceModel> existingProjectOptional = placeRepository.findById(id);

        if (existingProjectOptional.isPresent()) {
            PlaceModel existingProject = existingProjectOptional.get();
            existingProject.setState(place.getState());
            existingProject.setType(place.getType());
            existingProject.setPrice(place.getPrice());

            return placeRepository.save(existingProject);
        } else {
            // Manejar el caso donde no se encuentra el proyecto
            return null; // o lanza una excepción según tu lógica de negocio
        }
    }

    @Override
    public List<PlaceModel> getPlace() {
        return placeRepository.findAll();
    }

    @Override
    public Optional<PlaceModel> getPlaceById(String id) {
        return placeRepository.findById(id);
    }

    @Override
    public void deletePlace(String id) {
        placeRepository.deleteById(id);
    }
}
