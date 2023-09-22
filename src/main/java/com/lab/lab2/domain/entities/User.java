package com.lab.lab2.domain.entities;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
@EqualsAndHashCode
public class User extends AbstractEntity {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String lastPassword;
    private String roles;
    @OneToMany(fetch = FetchType.LAZY)
    private List<Ticket> tickets;
    @ManyToOne(fetch = FetchType.LAZY)
    private Dashboard dashboard;
}
