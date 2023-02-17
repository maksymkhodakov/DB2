package com.lab.lab2.mappers;

import com.lab.lab2.domain.data.CrewData;
import com.lab.lab2.domain.entities.Crew;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class CrewMapper implements Function<CrewData, Crew> {
    @Override
    public Crew apply(CrewData crewData) {
        return new Crew(
                crewData.getCrewName(),
                crewData.getRatingScore(),
                crewData.getTimetable(),
                crewData.getTrain(),
                crewData.getCrewMembers()
        );
    }
}
