package com.lab.lab2.mappers;

import com.lab.lab2.domain.data.CrewMemberData;
import com.lab.lab2.domain.entities.CrewMember;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class CrewMemberMapper implements Function<CrewMemberData, CrewMember> {
    @Override
    public CrewMember apply(CrewMemberData crewMemberData) {
        return new CrewMember(
                crewMemberData.getFirstName(),
                crewMemberData.getLastName(),
                crewMemberData.getAge(),
                crewMemberData.getYearsOfExperience(),
                crewMemberData.getPhoneNumber(),
                crewMemberData.getEmail(),
                crewMemberData.getCrew()
        );
    }
}
