package com.example.cargotransportspringboot.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String startLocation;
    private String endLocation;
    private LocalDateTime deliveryDeadline;
    @JsonIgnore
    @OneToMany(mappedBy = "route", cascade = CascadeType.ALL)
    private List<Checkpoint> checkpoints;
    @JsonIgnore
    @OneToMany(mappedBy = "route", cascade = CascadeType.ALL)
    private List<Cargo> cargo;
    @ManyToOne
    private Driver driver;
    @ManyToOne
    private Manager manager;
    @ManyToOne
    private Truck assignedTruck;

    public Route(String startLocation, String endLocation, LocalDateTime deliveryDeadline, Driver driver, Manager manager, Truck assignedTruck) {
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.deliveryDeadline = deliveryDeadline;
        this.driver = driver;
        this.manager = manager;
        this.assignedTruck = assignedTruck;
    }

    //    @Override
//    public String toString() {
//
//                "id=" + id +
//                ", startLocation='" + startLocation + '\'' +
//                ", endLocation='" + endLocation + '\'' +
//                ", deliveryDeadline=" + deliveryDeadline +
////                ", checkpoints=" + checkpoints +
////                ", cargo=" + cargo +
//                ", driver=" + driver +
//                '}';
//    }

    @Override
    public String toString() {
        return "Route(" +
                "id=" + id +
                ", startLocation='" + startLocation + '\'' +
                ", endLocation='" + endLocation + '\'' +
                ", deliveryDeadline=" + deliveryDeadline +
                ", driver=" + driver +
                ", manager=" + manager +
                ", assignedTruck=" + assignedTruck +
                ')';
    }
}
