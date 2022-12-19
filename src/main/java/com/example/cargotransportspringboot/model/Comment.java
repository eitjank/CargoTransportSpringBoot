package com.example.cargotransportspringboot.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String text;
    @JsonIgnore
    @OneToMany(mappedBy = "parentComment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> replies;
    @ManyToOne
    private Comment parentComment;
    @ManyToOne
    private Forum parentForum;
    @ManyToOne
    private User author;

    public Comment(String text, Comment parentComment, Forum parentForum, User author) {
        this.text = text;
        this.parentComment = parentComment;
        this.parentForum = parentForum;
        this.author = author;
    }

    @Override
    public String toString() {
        return "Comment(" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", parentComment=" + parentComment +
                ", parentForum=" + parentForum +
                ", author=" + author +
                ')';
    }
}
