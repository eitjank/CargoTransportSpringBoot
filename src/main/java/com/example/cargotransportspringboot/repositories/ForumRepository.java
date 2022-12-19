package com.example.cargotransportspringboot.repositories;

import com.example.cargotransportspringboot.model.Forum;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ForumRepository extends JpaRepository<Forum, Integer> {
}
