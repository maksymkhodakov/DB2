package com.lab.lab2.repository;

import com.lab.lab2.domain.entities.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripRepository extends JpaRepository<Trip, Long> {
}
