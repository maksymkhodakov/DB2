package com.lab.lab2.domain.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class CrewMemberDTO {
    private Long id;

    private LocalDateTime created;

    private LocalDateTime updated;

    private String firstName;

    private String lastName;

    private Integer age;

    private Integer yearsOfExperience;

    private String phoneNumber;

    private String email;

    private Long crewId;
}
