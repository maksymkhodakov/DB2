package com.lab.lab2.repository;

import com.lab.lab2.domain.entities.Train;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainRepository extends JpaRepository<Train, Long> {
}
