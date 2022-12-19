package com.example.cargotransportspringboot.controllers;

import com.example.cargotransportspringboot.model.Route;
import com.example.cargotransportspringboot.repositories.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/routes")
public class RouteController {

    @Autowired
    private RouteRepository routeRepository;

    @GetMapping(value = "/getAll")
    public @ResponseBody Iterable<Route> getAllRoutes() {
        return routeRepository.findAll();
    }

    @GetMapping(value = "/getAllByManager/{id}")
    public @ResponseBody Iterable<Route> getAllManagerRoutes(@PathVariable int id) {
        return routeRepository.getRoutesByManager_Id(id);
    }

    @GetMapping(value = "/getAllByDriver/{id}")
    public @ResponseBody Iterable<Route> getAllDriverRoutes(@PathVariable int id) {
        return routeRepository.getRoutesByDriver_Id(id);
    }

    @GetMapping(value = "/get/{id}")
    public @ResponseBody Optional<Route> getRouteById(@PathVariable Long id) {
        return routeRepository.findById(id);
    }

    @PostMapping(value = "/add")
    public @ResponseBody Route addNewRoute(@RequestBody Route route) {
        routeRepository.save(route);
        return route;
    }

    @DeleteMapping(value = "/delete/{id}")
    public @ResponseBody String deleteRouteById(@PathVariable Long id) {
        try {
            routeRepository.deleteById(id);
            return "Successfully deleted";
        } catch (Exception e){
            return "Not ok";
        }
    }

    @PutMapping(value = "/update/{id}")
    public @ResponseBody ResponseEntity<Route> updateRoute(@PathVariable Long id, @RequestBody Route route) {
        Optional<Route> routeData = routeRepository.findById(id);
        if (routeData.isPresent()) {
            Route updatedRoute = routeData.get();
            updatedRoute.setStartLocation(route.getStartLocation());
            updatedRoute.setEndLocation(route.getEndLocation());
            updatedRoute.setDeliveryDeadline(route.getDeliveryDeadline());
            return new ResponseEntity<Route>(routeRepository.save(updatedRoute), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
