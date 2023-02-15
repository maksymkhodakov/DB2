package com.lab.lab2.domain.data;

import com.lab.lab2.domain.entities.Crew;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CrewMemberData {
    private String firstName;

    private String lastName;

    private Integer age;

    private Integer yearsOfExperience;

    private String phoneNumber;

    private String email;

    private Crew crew;
}
