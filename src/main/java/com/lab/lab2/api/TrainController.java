package com.lab.lab2.api;

import com.lab.lab2.domain.DTO.TrainDTO;
import com.lab.lab2.domain.data.TrainData;
import com.lab.lab2.domain.entities.Train;
import com.lab.lab2.exceptions.TrainNotFoundException;
import com.lab.lab2.mappers.TrainMapper;
import com.lab.lab2.repository.TrainRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/train")
@RequiredArgsConstructor
public class TrainController {
    private final TrainMapper trainMapper;
    private final TrainRepository trainRepository;

    @PostMapping("/create")
    public ResponseEntity<Train> createTrain(@RequestBody @Valid TrainData trainData) {
        return ResponseEntity.ok(trainRepository.save(trainMapper.apply(trainData)));
    }

    @PutMapping("/update")
    public ResponseEntity<Train> updateTrain(@RequestParam Long id,
                                             @RequestBody @Valid TrainData trainData) {
        final var trainToUpdate = trainRepository.findById(id)
                .orElseThrow(() -> new TrainNotFoundException("Train with id: " + id +" not found!"));
        trainToUpdate.setName(trainData.getName());
        trainToUpdate.setSize(trainData.getSize());
        trainToUpdate.setColor(trainData.getColor());
        trainToUpdate.setType(trainData.getType());
        trainToUpdate.setCrews(trainData.getCrews());
        trainToUpdate.setTimetables(trainData.getTimetables());
        trainToUpdate.setUpdateDate();
        trainRepository.save(trainToUpdate);
        return ResponseEntity.ok(trainToUpdate);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<TrainDTO> getTrain(@PathVariable Long id) {
        return ResponseEntity.ok(trainRepository.fetchById(id)
                .orElseThrow(() -> new TrainNotFoundException("Train with id: " + id +" not found!")));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        trainRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
