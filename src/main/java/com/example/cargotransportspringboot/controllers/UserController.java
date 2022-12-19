package com.example.cargotransportspringboot.controllers;

import com.example.cargotransportspringboot.model.Driver;
import com.example.cargotransportspringboot.model.Manager;
import com.example.cargotransportspringboot.model.User;
import com.example.cargotransportspringboot.repositories.UserRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Properties;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping(value = "/getAll")
    public @ResponseBody Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping(value = "/get/{id}")
    public @ResponseBody EntityModel<User> getUserById(@PathVariable Integer id) {
        User user = userRepository.findById(id).orElseThrow();
        return EntityModel.of(user,
                linkTo(methodOn(UserController.class).getUserById(id)).withSelfRel(),
                linkTo(methodOn(UserController.class).getAllUsers()).withRel("users"));
    }

    @PostMapping(value = "/validateUser")
    public @ResponseBody User validateUser(@RequestBody String loginInfo) {
        Gson parser = new Gson();
        Properties properties = parser.fromJson(loginInfo, Properties.class);
        return userRepository.getUserByLoginAndPassword(properties.getProperty("login"), properties.getProperty("password"));
    }

    @PostMapping(value = "/addDriver")
    public @ResponseBody Driver addNewDriver(@RequestBody Driver driver) {
        userRepository.save(driver);
        return driver;
    }

    @PostMapping(value = "/addManager")
    public @ResponseBody Manager addNewManager(@RequestBody Manager manager) {
        userRepository.save(manager);
        return manager;
    }

    @DeleteMapping(value = "/delete/{id}")
    public @ResponseBody String deleteUserById(@PathVariable Integer id) {
        try {
            userRepository.deleteById(id);
            return "Successfully deleted";
        } catch (Exception e){
            return "Not ok";
        }
    }

    @PutMapping(value = "/updateManager/{id}")
    public @ResponseBody ResponseEntity<Manager> updateManager(@PathVariable Integer id, @RequestBody Manager manager) {
        Optional<User> userData = userRepository.findById(id);
        if (userData.isPresent()) {
            Manager updatedUser = (Manager) userData.get();
            updatedUser.setName(manager.getName());
            updatedUser.setLogin(manager.getLogin());
            updatedUser.setPassword(manager.getPassword());
            updatedUser.setBirthDate(manager.getBirthDate());
            updatedUser.setPhoneNumber(manager.getPhoneNumber());
            updatedUser.setEmail(manager.getEmail());
            return new ResponseEntity<Manager>(userRepository.save(updatedUser), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/updateDriver/{id}")
    public @ResponseBody ResponseEntity<Driver> updateDriver(@PathVariable Integer id, @RequestBody Driver driver) {
        Optional<User> userData = userRepository.findById(id);
        if (userData.isPresent()) {
            Driver updatedUser = (Driver) userData.get();
            updatedUser.setName(driver.getName());
            updatedUser.setLogin(driver.getLogin());
            updatedUser.setPassword(driver.getPassword());
            updatedUser.setBirthDate(driver.getBirthDate());
            updatedUser.setPhoneNumber(driver.getPhoneNumber());
            updatedUser.setDrivingLicenseValidUntilDate(driver.getDrivingLicenseValidUntilDate());
            return new ResponseEntity<Driver>(userRepository.save(updatedUser), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
