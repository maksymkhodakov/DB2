package com.lab.lab2.repository;

import com.lab.lab2.domain.DTO.CrewDTO;
import com.lab.lab2.domain.entities.Crew;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CrewRepository extends JpaRepository<Crew, Long> {
    @Query("select new com.lab.lab2.domain.DTO.CrewDTO(c.crewName, c.ratingScore)" +
            " from Crew c where c.id = :id")
    Optional<CrewDTO> fetchById(Long id);

    @Modifying
    @Transactional
    @Query(value = "update crew_members cm set cm.crew_id = :crewId where cm.id = :crewMemberId", nativeQuery = true)
    void joinMember(Long crewMemberId, Long crewId);
}
