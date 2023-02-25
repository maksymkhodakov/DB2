package com.lab.lab2.domain.DTO;

import com.lab.lab2.domain.enums.Color;
import com.lab.lab2.domain.enums.Type;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TrainDTO {
    private String name;

    private Integer size;

    private Color color;

    private Type type;
}
