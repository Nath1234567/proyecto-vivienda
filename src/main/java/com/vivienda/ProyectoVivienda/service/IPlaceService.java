package com.vivienda.ProyectoVivienda.service;

import com.vivienda.ProyectoVivienda.model.PlaceModel;

import java.util.List;
import java.util.Optional;

public interface IPlaceService {
    PlaceModel createPlace(PlaceModel place);

    List<PlaceModel> getPlace();

    PlaceModel updatePlace(String id, PlaceModel place);

    Optional<PlaceModel> getPlaceById(String id);

    void deletePlace(String id);
}
