package com.lab.lab2.domain.entities;

import com.lab.lab2.domain.enums.Color;
import com.lab.lab2.domain.enums.Type;
import jakarta.persistence.*;
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
    @Column(name = "name", nullable = false)
    private String name;

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
            name = "train_timetable",
            joinColumns = @JoinColumn(name = "train_id"),
            inverseJoinColumns = @JoinColumn(name = "timetable_id")
    )
    private List<Timetable> timetables = new ArrayList<>();

    public void addTimetable(Timetable timetable) {
        timetables.add(timetable);
        timetable.getTrains().add(this);
    }

    public void removeTimetable(Timetable timetable) {
        timetables.remove(timetable);
        timetable.getTrains().remove(this);
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
