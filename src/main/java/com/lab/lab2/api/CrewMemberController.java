package com.lab.lab2.api;

import com.lab.lab2.domain.DTO.CrewMemberDTO;
import com.lab.lab2.domain.data.CrewMemberData;
import com.lab.lab2.domain.entities.CrewMember;
import com.lab.lab2.exceptions.CrewMemberNotFound;
import com.lab.lab2.mappers.CrewMemberMapper;
import com.lab.lab2.repository.CrewMemberRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/crew-members")
@RequiredArgsConstructor
public class CrewMemberController {
    private final CrewMemberMapper crewMemberMapper;
    private final CrewMemberRepository crewMemberRepository;

    @PostMapping("/create")
    public ResponseEntity<CrewMember> create(@RequestBody @Valid CrewMemberData crewMemberData) {
        return ResponseEntity.ok(crewMemberRepository.save(crewMemberMapper.apply(crewMemberData)));
    }

    @PutMapping("/update")
    public ResponseEntity<CrewMember> update(@RequestParam Long id,
                                             @RequestBody @Valid CrewMemberData crewMemberData) {
        final var crewMemberToUpdate = crewMemberRepository.findById(id)
                .orElseThrow(() -> new CrewMemberNotFound("Crew member with id: " + id +" not found!"));
        crewMemberToUpdate.setFirstName(crewMemberData.getFirstName());
        crewMemberToUpdate.setLastName(crewMemberData.getLastName());
        crewMemberToUpdate.setEmail(crewMemberData.getEmail());
        crewMemberToUpdate.setPhoneNumber(crewMemberData.getPhoneNumber());
        crewMemberToUpdate.setAge(crewMemberData.getAge());
        crewMemberToUpdate.setYearsOfExperience(crewMemberData.getYearsOfExperience());
        crewMemberToUpdate.setCrew(crewMemberData.getCrew());
        crewMemberToUpdate.setUpdateDate();
        crewMemberRepository.save(crewMemberToUpdate);
        return ResponseEntity.ok(crewMemberToUpdate);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<CrewMemberDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(crewMemberRepository.fetchById(id)
                .orElseThrow(() -> new CrewMemberNotFound("Crew member with id: " + id +" not found!")));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        crewMemberRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
