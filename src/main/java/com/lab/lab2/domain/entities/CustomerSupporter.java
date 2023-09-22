package com.lab.lab2.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customer_supporters")
@EqualsAndHashCode
public class CustomerSupporter extends AbstractEntity {

    private String name;

    @Column(name = "employee_id")
    private UUID employeeId;

    private String actions;

    @ManyToOne
    private SupportService supportService;
}
