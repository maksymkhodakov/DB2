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
@Table(name = "crews")
@EqualsAndHashCode
public class Crew extends AbstractEntity {
    @Column(name = "crew_name", nullable = false)
    private String crewName;

    @Column(name = "rating", nullable = false)
    private Integer ratingScore;

    @ManyToOne(fetch = FetchType.LAZY)
    private Timetable timetable;

    @ManyToOne(fetch = FetchType.LAZY)
    private Train train;

    @OneToMany(
            mappedBy = "crew",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<CrewMember> crewMembers = new ArrayList<>();

    public void addCrewMember(CrewMember crewMember) {
        crewMembers.add(crewMember);
        crewMember.setCrew(this);
    }

    public void removeCrewMember(CrewMember crewMember) {
        crewMembers.remove(crewMember);
        crewMember.setCrew(null);
    }
}