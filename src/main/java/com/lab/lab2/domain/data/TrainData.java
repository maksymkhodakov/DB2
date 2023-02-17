package com.lab.lab2.domain.data;

import com.lab.lab2.domain.entities.Crew;
import com.lab.lab2.domain.entities.Timetable;
import com.lab.lab2.domain.enums.Color;
import com.lab.lab2.domain.enums.Type;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class TrainData {
    @NotNull
    private String name;

    @NotNull
    private Integer size;

    @NotNull
    private Color color;

    @NotNull
    private Type type;

    private List<Crew> crews;

    private List<Timetable> timetables;
}
