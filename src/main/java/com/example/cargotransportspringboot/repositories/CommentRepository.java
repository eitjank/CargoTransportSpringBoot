package com.example.cargotransportspringboot.repositories;

import com.example.cargotransportspringboot.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
}
