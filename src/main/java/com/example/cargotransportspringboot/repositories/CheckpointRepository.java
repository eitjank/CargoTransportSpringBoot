package com.example.cargotransportspringboot.repositories;

import com.example.cargotransportspringboot.model.Checkpoint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckpointRepository extends JpaRepository<Checkpoint, Long> {
}
