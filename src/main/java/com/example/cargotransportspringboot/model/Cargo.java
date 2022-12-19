package com.example.cargotransportspringboot.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cargo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double weight;
    @ManyToOne
    private Route route;

    public Cargo(String name, double weight, Route route) {
        this.name = name;
        this.weight = weight;
        this.route = route;
    }
}
