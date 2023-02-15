package com.lab.lab2.repository;

import com.lab.lab2.domain.entities.Crew;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CrewRepository extends JpaRepository<Crew, Long> {
}
