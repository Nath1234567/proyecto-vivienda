package com.vivienda.ProyectoVivienda.controller;

import com.vivienda.ProyectoVivienda.exception.CustomException;
import com.vivienda.ProyectoVivienda.model.PlaceModel;
import com.vivienda.ProyectoVivienda.model.ProjectModel;
import com.vivienda.ProyectoVivienda.service.IPlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/place")
@Validated
public class PlaceController {

    @Autowired
    private IPlaceService IPlaceService;

    @PostMapping
    public PlaceModel createPlace(@RequestBody PlaceModel place) {
        return IPlaceService.createPlace(place);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlaceModel> updatePlace(@PathVariable String id, @RequestBody PlaceModel place) {
        PlaceModel updatePlace = IPlaceService.updatePlace(id, place);

        if (updatePlace != null) {
            return ResponseEntity.ok(updatePlace);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/api/place")
    public List<PlaceModel> getPlace() {
        return IPlaceService.getPlace();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlaceModel> getPlaceById(@PathVariable("id") String id) {
        Optional<PlaceModel> place = IPlaceService.getPlaceById(id);
        if (place.isPresent()) {
            return new ResponseEntity<>(place.get(), HttpStatus.OK);
        } else {
            throw new CustomException("404", "Place No Found");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlace(@PathVariable("id") String id) {
        IPlaceService.deletePlace(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}