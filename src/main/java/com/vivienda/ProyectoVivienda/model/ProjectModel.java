package com.vivienda.ProyectoVivienda.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "projects")
@Getter
@Setter
public class ProjectModel {
    @Id
    private String id;
    private String name;
    private String type;
    private String responsible;
    private String startDate;
    private String endDate;
}
