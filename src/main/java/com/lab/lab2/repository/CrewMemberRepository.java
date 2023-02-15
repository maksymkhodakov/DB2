package com.lab.lab2.repository;

import com.lab.lab2.domain.entities.CrewMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CrewMemberRepository extends JpaRepository<CrewMember, Long> {
}
