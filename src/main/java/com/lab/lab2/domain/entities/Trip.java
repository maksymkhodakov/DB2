package com.lab.lab2.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "trips_info_test")
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
    private Platform platformDeparture;

    @OneToOne(fetch = FetchType.LAZY)
    private Platform platformArrival;

    @ManyToOne(fetch = FetchType.LAZY)
    private Timetable timetable;
}