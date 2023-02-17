package com.lab.lab2.domain.DTO;

import com.lab.lab2.domain.enums.Type;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class TripDataDTO {
    private String tripName;
    private LocalDate departureDate;
    private LocalDate arrivalDate;
    private Type type;
}
