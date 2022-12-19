package com.example.cargotransportspringboot.controllers;

import com.example.cargotransportspringboot.model.Checkpoint;
import com.example.cargotransportspringboot.repositories.CheckpointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/checkpoints")
public class CheckpointController {

    @Autowired
    private CheckpointRepository checkpointRepository;

    @GetMapping(value = "/getAll")
    public @ResponseBody Iterable<Checkpoint> getAllCheckpoints() {
        return checkpointRepository.findAll();
    }

    @GetMapping(value = "/get/{id}")
    public @ResponseBody Optional<Checkpoint> getCheckpointById(@PathVariable Long id) {
        return checkpointRepository.findById(id);
    }

    @PostMapping(value = "/add")
    public @ResponseBody Checkpoint addNewCheckpoint(@RequestBody Checkpoint checkpoint) {
        checkpointRepository.save(checkpoint);
        return checkpoint;
    }

    @DeleteMapping(value = "/delete/{id}")
    public @ResponseBody String deleteCheckpointById(@PathVariable Long id) {
        try {
            checkpointRepository.deleteById(id);
            return "Successfully deleted";
        } catch (Exception e){
            return "Not ok";
        }
    }

    @PutMapping(value = "/update/{id}")
    public @ResponseBody ResponseEntity<Checkpoint> updateCheckpoint(@PathVariable Long id, @RequestBody Checkpoint checkpoint) {
        Optional<Checkpoint> checkpointData = checkpointRepository.findById(id);
        if (checkpointData.isPresent()) {
            Checkpoint updatedCheckpoint = checkpointData.get();
            updatedCheckpoint.setLocation(checkpoint.getLocation());
            updatedCheckpoint.setTimeArrived(checkpoint.getTimeArrived());
            updatedCheckpoint.setTimeDeparted(checkpoint.getTimeDeparted());
            return new ResponseEntity<Checkpoint>(checkpointRepository.save(updatedCheckpoint), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
