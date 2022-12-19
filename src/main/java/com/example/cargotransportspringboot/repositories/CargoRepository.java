package com.example.cargotransportspringboot.repositories;

import com.example.cargotransportspringboot.model.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CargoRepository extends JpaRepository<Cargo, Long> {
}
