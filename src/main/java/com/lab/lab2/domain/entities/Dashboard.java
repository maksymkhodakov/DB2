package com.lab.lab2.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "dashboards")
@EqualsAndHashCode
public class Dashboard extends AbstractEntity {

    private String code;

    private String description;

    @OneToMany
    private List<Trip> trips;

    @OneToMany
    private List<User> users;

}
