package com.lab.lab2.domain.entities;

import com.lab.lab2.domain.enums.Color;
import com.lab.lab2.domain.enums.Type;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "trains")
@EqualsAndHashCode
public class Train extends AbstractEntity {
    @NotBlank
    @Column(name = "name", nullable = false)
    private String name;

    @NotBlank
    @Column(name = "size", nullable = false)
    private Integer size;

    @Column(name = "color")
    @Enumerated(value = EnumType.STRING)
    private Color color;

    @Column(name = "type")
    @Enumerated(value = EnumType.STRING)
    private Type type;

    @OneToMany(
            mappedBy = "train",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Crew> crews = new ArrayList<>();

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(
            name = "train_trip",
            joinColumns = @JoinColumn(name = "train_id"),
            inverseJoinColumns = @JoinColumn(name = "trip_id")
    )
    private List<Trip> trips = new ArrayList<>();

    public void addTrip(Trip trip) {
        trips.add(trip);
        trip.getTrains().add(this);
    }

    public void removeTrip(Trip trip) {
        trips.remove(trip);
        trip.getTrains().remove(this);
    }

    public void addCrew(Crew crew) {
        crews.add(crew);
        crew.setTrain(this);
    }

    public void removeCrew(Crew crew) {
        crews.remove(crew);
        crew.setTrain(null);
    }
}
