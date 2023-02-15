package com.lab.lab2.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "timetables")
public class Timetable extends AbstractEntity {

    @Column(name = "country")
    private String country;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "is_working")
    private Boolean isWorking;

    @OneToMany(
            mappedBy = "timetable",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Crew> crews = new ArrayList<>();

    @OneToMany(
            mappedBy = "timetable",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Trip> trips = new ArrayList<>();

    public void addCrew(Crew crew) {
        crews.add(crew);
        crew.setTimetable(this);
    }

    public void removeCrew(Crew crew) {
        crews.remove(crew);
        crew.setTimetable(null);
    }

    public void addTrip(Trip trip) {
        trips.add(trip);
        trip.setTimetable(this);
    }

    public void removeTrip(Trip trip) {
        trips.remove(trip);
        trip.setTimetable(null);
    }
}
