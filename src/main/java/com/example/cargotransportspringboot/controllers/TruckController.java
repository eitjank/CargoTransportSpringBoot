package com.example.cargotransportspringboot.controllers;

import com.example.cargotransportspringboot.model.Truck;
import com.example.cargotransportspringboot.repositories.TruckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/trucks")
public class TruckController {

    @Autowired
    private TruckRepository truckRepository;

    @GetMapping(value = "/getAll")
    public @ResponseBody Iterable<Truck> getAllTrucks() {
        return truckRepository.findAll();
    }

    @GetMapping(value = "/get/{id}")
    public @ResponseBody Optional<Truck> getTruckById(@PathVariable int id) {
        return truckRepository.findById(id);
    }

    @PostMapping(value = "/add")
    public @ResponseBody Truck addNewTruck(@RequestBody Truck truck) {
        truckRepository.save(truck);
        return truck;
    }

    @DeleteMapping(value = "/delete/{id}")
    public @ResponseBody String deleteTruckById(@PathVariable int id) {
        try {
            truckRepository.deleteById(id);
            return "Successfully deleted";
        } catch (Exception e){
            return "Not ok";
        }
    }

    @PutMapping(value = "/update/{id}")
    public @ResponseBody ResponseEntity<Truck> updateTruck(@PathVariable int id, @RequestBody Truck truck) {
        Optional<Truck> truckData = truckRepository.findById(id);
        if (truckData.isPresent()) {
            Truck updatedTruck = truckData.get();
            updatedTruck.setModel(truck.getModel());
            updatedTruck.setCarBrand(truck.getCarBrand());
            updatedTruck.setManufactureYear(truck.getManufactureYear());
            return new ResponseEntity<Truck>(truckRepository.save(updatedTruck), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
