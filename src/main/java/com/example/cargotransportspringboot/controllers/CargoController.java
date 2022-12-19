package com.example.cargotransportspringboot.controllers;

import com.example.cargotransportspringboot.model.Cargo;
import com.example.cargotransportspringboot.repositories.CargoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/cargo")
public class CargoController {
    
    @Autowired
    private CargoRepository cargoRepository;

    @GetMapping(value = "/getAll")
    public @ResponseBody Iterable<Cargo> getAllCargos() {
        return cargoRepository.findAll();
    }

    @GetMapping(value = "/get/{id}")
    public @ResponseBody Optional<Cargo> getCargoById(@PathVariable Long id) {
        return cargoRepository.findById(id);
    }

    @PostMapping(value = "/add")
    public @ResponseBody Cargo addNewCargo(@RequestBody Cargo cargo) {
        cargoRepository.save(cargo);
        return cargo;
    }

    @DeleteMapping(value = "/delete/{id}")
    public @ResponseBody String deleteCargoById(@PathVariable Long id) {
        try {
            cargoRepository.deleteById(id);
            return "Successfully deleted";
        } catch (Exception e){
            return "Not ok";
        }
    }

    @PutMapping(value = "/update/{id}")
    public @ResponseBody ResponseEntity<Cargo> updateCargo(@PathVariable Long id, @RequestBody Cargo cargo) {
        Optional<Cargo> cargoData = cargoRepository.findById(id);
        if (cargoData.isPresent()) {
            Cargo updatedCargo = cargoData.get();
            updatedCargo.setName(cargo.getName());
            updatedCargo.setWeight(cargo.getWeight());
            return new ResponseEntity<Cargo>(cargoRepository.save(updatedCargo), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
