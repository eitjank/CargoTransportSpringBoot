package com.example.cargotransportspringboot.controllers;

import com.example.cargotransportspringboot.model.Forum;
import com.example.cargotransportspringboot.repositories.ForumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/forums")
public class ForumController {

    @Autowired
    private ForumRepository forumRepository;

    @GetMapping(value = "/getAll")
    public @ResponseBody Iterable<Forum> getAllForums() {
        return forumRepository.findAll();
    }

    @GetMapping(value = "/get/{id}")
    public @ResponseBody Optional<Forum> getForumById(@PathVariable int id) {
        return forumRepository.findById(id);
    }

    @PostMapping(value = "/add")
    public @ResponseBody Forum addNewForum(@RequestBody Forum forum) {
        forumRepository.save(forum);
        return forum;
    }

    @DeleteMapping(value = "/delete/{id}")
    public @ResponseBody String deleteForumById(@PathVariable int id) {
        try {
            forumRepository.deleteById(id);
            return "Successfully deleted";
        } catch (Exception e){
            return "Not ok";
        }
    }

    @PutMapping(value = "/update/{id}")
    public @ResponseBody ResponseEntity<Forum> updateForum(@PathVariable int id, @RequestBody Forum forum) {
        Optional<Forum> forumData = forumRepository.findById(id);
        if (forumData.isPresent()) {
            Forum updatedForum = forumData.get();
            updatedForum.setTitle(forum.getTitle());
            updatedForum.setText(forum.getText());
            return new ResponseEntity<Forum>(forumRepository.save(updatedForum), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
