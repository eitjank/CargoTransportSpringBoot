package com.example.cargotransportspringboot.repositories;

import com.example.cargotransportspringboot.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RouteRepository extends JpaRepository<Route, Long> {
    List<Route> getRoutesByManager_Id(int id);
    List<Route> getRoutesByDriver_Id(int id);
}
