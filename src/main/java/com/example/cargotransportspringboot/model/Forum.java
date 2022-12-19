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
public class Forum {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String text;
    @ManyToOne
    private User author;
    @JsonIgnore
    @OneToMany(mappedBy = "parentForum", cascade = CascadeType.ALL)

    private List<Comment> comments;

    public Forum(String title, String text, User author) {
        this.title = title;
        this.text = text;
        this.author = author;
    }

    @Override
    public String toString() {
        return "Forum(" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", author=" + author +
                ')';
    }
}
