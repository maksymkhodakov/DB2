package com.lab.lab2.domain.entities;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "continents")
@EqualsAndHashCode
public class Continent extends AbstractEntity {
    private String name;

    private BigDecimal population;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Country> countries;

    private double area;
}
