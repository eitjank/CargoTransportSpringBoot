package com.example.cargotransportspringboot.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Checkpoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String location;
    private LocalDateTime timeArrived;
    private LocalDateTime timeDeparted;
    @ManyToOne
    private Route route;

    public Checkpoint(String location, LocalDateTime timeArrived, LocalDateTime timeDeparted, Route route) {
        this.location = location;
        this.timeArrived = timeArrived;
        this.timeDeparted = timeDeparted;
        this.route = route;
    }
}
