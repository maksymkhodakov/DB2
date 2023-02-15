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
@Table(name = "cities")
@EqualsAndHashCode
public class City extends AbstractEntity {
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "short_description")
    private String shortDescription;

    @Column(name = "administrative_region", nullable = false)
    private String administrativeRegion;

    @OneToMany(
            mappedBy = "city",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Platform> platforms = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private Country country;

    public void addPlatform(Platform platform) {
        platforms.add(platform);
        platform.setCity(this);
    }

    public void removePlatform(Platform platform) {
        platforms.remove(platform);
        platform.setCity(null);
    }
}
