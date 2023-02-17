package com.lab.lab2.domain.data;

import com.lab.lab2.annotation.ValidPhone;
import com.lab.lab2.domain.entities.Crew;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CrewMemberData {
    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private Integer age;

    @NotNull
    private Integer yearsOfExperience;

    @ValidPhone
    @NotNull
    private String phoneNumber;

    @Email
    private String email;

    private Crew crew;
}
