package com.lab.lab2.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "trips")
@EqualsAndHashCode
public class Trip extends AbstractEntity {
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "notes")
    private String notes;

    @Column(name = "departure_date")
    private LocalDateTime departureDate;

    @Column(name = "arrival_date")
    private LocalDateTime arrivalDate;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Platform platformDeparture;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Platform platformArrival;

    @ManyToOne(fetch = FetchType.LAZY)
    private Timetable timetable;

    @ManyToMany(mappedBy = "trips")
    private List<Train> trains = new ArrayList<>();
}