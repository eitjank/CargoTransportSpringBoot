package com.example.cargotransportspringboot.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public abstract class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique=true)
    private String login;
    private String password;
    private String name;
    private String surname;
    private LocalDate birthDate;
    private String phoneNumber;
    @JsonIgnore
    @OneToMany(mappedBy = "author")
    private List<Comment> authoredComments;
    @JsonIgnore
    @OneToMany(mappedBy = "author")
    private List<Forum> authoredForums;

    public User(String login, String password, String name, String surname, LocalDate birthDate, String phoneNumber) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birthDate=" + birthDate +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
