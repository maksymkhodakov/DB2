package com.lab.lab2.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_history")
@EqualsAndHashCode
public class UserHistory extends AbstractEntity {

    @Column(name = "last_book_date")
    private LocalDate lastBook;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Ticket> tickets;
}
