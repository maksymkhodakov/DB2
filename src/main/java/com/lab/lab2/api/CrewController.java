package com.lab.lab2.api;

import com.lab.lab2.domain.DTO.CrewDTO;
import com.lab.lab2.domain.data.CrewData;
import com.lab.lab2.domain.entities.Crew;
import com.lab.lab2.exceptions.CrewNotFound;
import com.lab.lab2.mappers.CrewMapper;
import com.lab.lab2.repository.CrewRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static java.util.Objects.isNull;

@RestController
@RequestMapping("/api/crews")
@RequiredArgsConstructor
public class CrewController {
    private final CrewMapper crewMapper;
    private final CrewRepository crewRepository;

    @PostMapping("/create")
    public ResponseEntity<Crew> create(@RequestBody @Valid CrewData crewData) {
        return ResponseEntity.ok(crewRepository.save(crewMapper.apply(crewData)));
    }

    @PutMapping("/update")
    public ResponseEntity<Crew> update(@RequestParam Long id,
                                       @RequestBody @Valid CrewData crewData) {
        final var crewToUpdate = crewRepository.findById(id)
                .orElseThrow(() -> new CrewNotFound("Crew with id: " + id +" not found!"));
        crewToUpdate.setCrewName(crewData.getCrewName());
        crewToUpdate.setRatingScore(crewData.getRatingScore());
        crewToUpdate.setTimetable(crewData.getTimetable());
        crewToUpdate.setTrain(crewData.getTrain());
        if (isNull(crewData.getCrewMembers())) {
            crewToUpdate.setCrewMembers(crewToUpdate.getCrewMembers());
        } else {
            crewData.getCrewMembers().forEach(crewToUpdate::addCrewMember);
        }
        crewToUpdate.setUpdateDate();
        crewRepository.save(crewToUpdate);
        return ResponseEntity.ok(crewToUpdate);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<CrewDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(crewRepository.fetchById(id)
                .orElseThrow(() -> new CrewNotFound("Crew with id: " + id +" not found!")));
    }

    @PutMapping("/join-member-to-crew")
    public ResponseEntity<Void> joinMemberToCrew(@RequestParam Long crewMemberId,
                                                 @RequestParam Long crewId) {
        crewRepository.joinMember(crewMemberId, crewId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        crewRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
