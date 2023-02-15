package com.lab.lab2.api;

import com.lab.lab2.domain.data.CrewMemberData;
import com.lab.lab2.domain.entities.CrewMember;
import com.lab.lab2.repository.CrewMemberRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
@RequiredArgsConstructor
public class CrewMemberController {
    private final CrewMemberRepository crewMemberRepository;

    @PostMapping("create/")
    public CrewMember save(@Valid @RequestBody CrewMemberData crewMemberData) {
        final var crewMember = new CrewMember(
                crewMemberData.getFirstName(),
                crewMemberData.getLastName(),
                crewMemberData.getAge(),
                crewMemberData.getYearsOfExperience(),
                crewMemberData.getPhoneNumber(),
                crewMemberData.getEmail(),
                crewMemberData.getCrew()
        );
        return crewMemberRepository.save(crewMember);
    }

    @GetMapping("get/{id}")
    public CrewMember getByID(@PathVariable("id") Long id) {
        return crewMemberRepository.findById(id).orElseThrow();
    }
}
