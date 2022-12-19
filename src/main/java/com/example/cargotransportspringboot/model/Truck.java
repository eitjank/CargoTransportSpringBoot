package com.example.cargotransportspringboot.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Truck {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String carBrand;
    private String model;
    private int manufactureYear;
    @JsonIgnore
    @OneToMany(mappedBy = "assignedTruck")
    private List<Route> routeAssignedTo;

    public Truck(String carBrand, String model, int manufactureYear) {
        this.carBrand = carBrand;
        this.model = model;
        this.manufactureYear = manufactureYear;
    }

    @Override
    public String toString() {
        return "Truck(" +
                "id=" + id +
                ", carBrand='" + carBrand + '\'' +
                ", model='" + model + '\'' +
                ", manufactureYear=" + manufactureYear +
                ')';
    }
}
