package com.lab.lab2.repository;

import com.lab.lab2.domain.entities.Platform;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlatformRepository extends JpaRepository<Platform, Long> {
}
