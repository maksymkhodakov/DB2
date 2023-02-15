package com.lab.lab2.repository;

import com.lab.lab2.domain.entities.Timetable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimetableRepository extends JpaRepository<Timetable, Long> {
}
