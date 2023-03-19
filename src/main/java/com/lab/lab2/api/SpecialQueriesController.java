package com.lab.lab2.api;

import com.lab.lab2.domain.DTO.*;
import com.lab.lab2.domain.enums.Color;
import com.lab.lab2.domain.enums.Type;
import com.lab.lab2.repository.CountryRepository;
import com.lab.lab2.repository.CrewMemberRepository;
import com.lab.lab2.repository.TrainRepository;
import com.lab.lab2.repository.TripRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping("/api/special-queries")
@RequiredArgsConstructor
public class SpecialQueriesController {
    private final CrewMemberRepository crewMemberRepository;
    private final TrainRepository trainRepository;
    private final TripRepository tripRepository;
    private final CountryRepository countryRepository;

    @GetMapping("/special-query-1")
    public CrewMemberDTO runQuery1(@RequestParam Integer year,
                                   @RequestParam Integer month,
                                   @RequestParam Integer day,
                                   @RequestParam Integer ratingScore) {
        LocalDateTime dateTime = LocalDateTime.of(year, month, day, 0, 0);
        return crewMemberRepository.specialQuery1(dateTime, ratingScore);
    }

    @GetMapping("/special-query-2")
    public AvgCrewTrainAnalyticsDTO runQuery2(@RequestParam List<Color> colors,
                                              @RequestParam Integer trainMaxSize) {
        return crewMemberRepository.specialQuery2(colors, trainMaxSize);
    }

    @GetMapping("/special-query-3")
    public List<DataWrapper> runQuery3(@RequestParam Type type,
                                       @RequestParam Integer max,
                                       @RequestParam Integer min) {
        return trainRepository
                .runQuery3(type, min, max)
                .stream()
                .map(DataWrapper::new)
                .toList();
    }

    @GetMapping("/special-query-4")
    public TrainSizesDTO runQuery4(@RequestParam Integer rating) {
        return trainRepository.runQuery4(rating);
    }

    @GetMapping("/special-query-5")
    public List<DataWrapper> runQuery5(@RequestParam String description) {
        return tripRepository
                .runQuery5(description)
                .stream()
                .map(DataWrapper::new)
                .toList();
    }

    @GetMapping("/special-query-6")
    public List<DataWrapper> runQuery6(@RequestParam String description) {
        return countryRepository
                .runQuery6(description)
                .stream()
                .map(DataWrapper::new)
                .toList();
    }

    @GetMapping("/special-query-7")
    public List<DataWrapper> runQuery7(@RequestParam String countryName) {
        return tripRepository
                .runQuery7(countryName)
                .stream()
                .map(DataWrapper::new)
                .toList();
    }

    @PostMapping("/special-query-8")
    public List<DataWrapper> runQuery8(@RequestBody TripDataDTO tripData) {
        return tripRepository.runQuery8(tripData.getTripName(), tripData.getDepartureDate().toString(),
                tripData.getArrivalDate().toString(), tripData.getType().name())
                .stream()
                .map(DataWrapper::new)
                .toList();
    }
}
