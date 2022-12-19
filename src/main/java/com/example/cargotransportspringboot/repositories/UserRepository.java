package com.example.cargotransportspringboot.repositories;

import com.example.cargotransportspringboot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User getUserByLoginAndPassword(String login, String password);

}
