package com.example.cargotransportspringboot.repositories;

import com.example.cargotransportspringboot.model.Truck;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TruckRepository extends JpaRepository<Truck, Integer> {
}
