package com.lab.lab2.domain.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tickets")
@EqualsAndHashCode
public class Ticket extends AbstractEntity {
    private String code;

    private String description;

    @OneToOne
    @JoinColumn(name = "trip_id")
    private Trip trip;

    @ManyToOne
    private UserHistory userHistory;
}
