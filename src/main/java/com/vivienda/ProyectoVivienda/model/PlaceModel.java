package com.vivienda.ProyectoVivienda.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "place")

@Getter
@Setter
public class PlaceModel {
    @Id
    private String id;
    private String type;
    private Float price;
    private String state;
}
