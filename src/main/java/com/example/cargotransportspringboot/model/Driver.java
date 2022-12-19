package com.example.cargotransportspringboot.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Driver extends User {

    private LocalDate drivingLicenseValidUntilDate;

    @JsonIgnore
    @OneToMany(mappedBy = "driver")
    private List<Route> routes;

    public Driver(String login, String password, String name, String surname, LocalDate birthDate, String phoneNumber,
                  LocalDate drivingLicenseValidUntilDate) {
        super(login, password, name, surname, birthDate, phoneNumber);
        this.drivingLicenseValidUntilDate = drivingLicenseValidUntilDate;
    }

    @Override
    public String toString() {
        return "Driver(" +
                "id=" + getId() +
                ", login='" + getLogin() + '\'' +
                ", name='" + getName() + '\'' +
                ", surname='" + getSurname() + '\'' +
                ')';
    }
}
