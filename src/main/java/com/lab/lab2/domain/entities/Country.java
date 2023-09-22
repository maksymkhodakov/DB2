package com.lab.lab2.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "countries")
@EqualsAndHashCode
public class Country extends AbstractEntity {
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "country_code", nullable = false)
    private String code;

    @Column(name = "description")
    private String description;

    @OneToMany(
            mappedBy = "country",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<City> cities = new ArrayList<>();

    @ManyToOne
    private Continent continent;

    public void addCity(City city) {
        cities.add(city);
        city.setCountry(this);
    }

    public void removeCity(City city) {
        cities.remove(city);
        city.setCountry(null);
    }
}
