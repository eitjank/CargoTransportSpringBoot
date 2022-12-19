package com.example.cargotransportspringboot.controllers;

import com.example.cargotransportspringboot.model.Comment;
import com.example.cargotransportspringboot.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/comments")
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;

    @GetMapping(value = "/getAll")
    public @ResponseBody Iterable<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    @GetMapping(value = "/get/{id}")
    public @ResponseBody Optional<Comment> getCommentById(@PathVariable int id) {
        return commentRepository.findById(id);
    }

    @PostMapping(value = "/add")
    public @ResponseBody Comment addNewComment(@RequestBody Comment comment) {
        commentRepository.save(comment);
        return comment;
    }

    @DeleteMapping(value = "/delete/{id}")
    public @ResponseBody String deleteCommentById(@PathVariable int id) {
        try {
            commentRepository.deleteById(id);
            return "Successfully deleted";
        } catch (Exception e){
            return "Not ok";
        }
    }

    @PutMapping(value = "/update/{id}")
    public @ResponseBody ResponseEntity<Comment> updateComment(@PathVariable int id, @RequestBody Comment comment) {
        Optional<Comment> commentData = commentRepository.findById(id);
        if (commentData.isPresent()) {
            Comment updatedComment = commentData.get();
            updatedComment.setText(comment.getText());
            return new ResponseEntity<Comment>(commentRepository.save(updatedComment), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
