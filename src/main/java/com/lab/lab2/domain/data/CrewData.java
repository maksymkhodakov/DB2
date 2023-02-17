package com.lab.lab2.domain.data;

import com.lab.lab2.domain.entities.CrewMember;
import com.lab.lab2.domain.entities.Timetable;
import com.lab.lab2.domain.entities.Train;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class CrewData {
    @NotNull
    private String crewName;

    @NotNull
    private Integer ratingScore;

    private Timetable timetable;

    private Train train;

    private List<CrewMember> crewMembers;
}
