package com.lab.lab2.api;

import com.lab.lab2.domain.DTO.DataWrapper;
import com.lab.lab2.domain.enums.Color;
import com.lab.lab2.domain.enums.Type;
import com.lab.lab2.repository.*;
import com.lab.lab2.services.ExcelWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static com.lab.lab2.services.impl.ExcelWriterImpl.DEFAULT_DEPTH;
import static com.lab.lab2.services.impl.ExcelWriterImpl.EXCEL_MIME_TYPE;

@Controller
@RequiredArgsConstructor
public class WebController {
    private final CrewRepository crewRepository;
    private final CrewMemberRepository crewMemberRepository;
    private final TrainRepository trainRepository;
    private final TripRepository tripRepository;
    private final CountryRepository countryRepository;
    private final ExcelWriter excelWriter;


    @GetMapping("/index")
    public String index() {
        return "start";
    }

    @PostMapping("/download-crews")
    public ResponseEntity<Resource> downloadCrews() {
        final var crews = crewRepository.fetchAll();
        final var file = excelWriter.writeToExcel("crews.xlsx", EXCEL_MIME_TYPE, crews);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, file.getContentDispositionHeader())
                .contentType(MediaType.parseMediaType(file.getContentType()))
                .body(file.getResource());
    }

    @PostMapping("/download-crew-members")
    public ResponseEntity<Resource> downloadCrewMembers() {
        final var crewMembers = crewMemberRepository.fetchAll();
        final var file = excelWriter.writeToExcel("crew-members.xlsx", EXCEL_MIME_TYPE, crewMembers);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, file.getContentDispositionHeader())
                .contentType(MediaType.parseMediaType(file.getContentType()))
                .body(file.getResource());
    }

    @PostMapping("/download-trains")
    public ResponseEntity<Resource> downloadTrains() {
        final var trains = trainRepository.fetchAll();
        final var file = excelWriter.writeToExcel("trains.xlsx", EXCEL_MIME_TYPE, trains);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, file.getContentDispositionHeader())
                .contentType(MediaType.parseMediaType(file.getContentType()))
                .body(file.getResource());
    }

    @PostMapping("/query1")
    public ResponseEntity<Resource> query1(@RequestParam String year,
                                           @RequestParam String month,
                                           @RequestParam String day,
                                           @RequestParam String ratingScore) {
        String errors;
        try {
            LocalDateTime dateTime = LocalDateTime.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day), 0, 0);
            final var crewMember = crewMemberRepository.specialQuery1(dateTime, Integer.parseInt(ratingScore));
            final var file = excelWriter.writeToExcel(excelWriter.generateExcelFileName(DEFAULT_DEPTH), EXCEL_MIME_TYPE, List.of(crewMember));
            return ResponseEntity
                    .ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, file.getContentDispositionHeader())
                    .contentType(MediaType.parseMediaType(file.getContentType()))
                    .body(file.getResource());
        } catch (Exception e) {
            errors = e.getMessage();
            return ResponseEntity.ok(new ByteArrayResource(errors.getBytes()));
        }
    }

    @PostMapping("/query2")
    public ResponseEntity<Resource> query2(@RequestParam String colors,
                                           @RequestParam String trainMaxSize) {
        String errors;
        try {
            List<Color> list = Arrays.stream(colors.split(",")).map(Color::valueOf).toList();
            final var analytics = crewMemberRepository.specialQuery2(list, Integer.parseInt(trainMaxSize));
            final var file = excelWriter.writeToExcel(excelWriter.generateExcelFileName(DEFAULT_DEPTH), EXCEL_MIME_TYPE, List.of(analytics));
            return ResponseEntity
                    .ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, file.getContentDispositionHeader())
                    .contentType(MediaType.parseMediaType(file.getContentType()))
                    .body(file.getResource());
        } catch (Exception e) {
            errors = e.getMessage();
            return ResponseEntity.ok(new ByteArrayResource(errors.getBytes()));
        }
    }

    @PostMapping("/query3")
    public ResponseEntity<Resource> query3(@RequestParam String type,
                                           @RequestParam String max,
                                           @RequestParam String min) {
        String errors;
        try {
            final var trainsData = trainRepository.runQuery3(Type.valueOf(type), Integer.parseInt(min), Integer.parseInt(max))
                    .stream()
                    .map(DataWrapper::new)
                    .toList();
            final var file = excelWriter.writeToExcel(excelWriter.generateExcelFileName(DEFAULT_DEPTH), EXCEL_MIME_TYPE, trainsData);
            return ResponseEntity
                    .ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, file.getContentDispositionHeader())
                    .contentType(MediaType.parseMediaType(file.getContentType()))
                    .body(file.getResource());
        } catch (Exception e) {
            errors = e.getMessage();
            return ResponseEntity.ok(new ByteArrayResource(errors.getBytes()));
        }
    }

    @PostMapping("/query4")
    public ResponseEntity<Resource> query4(@RequestParam String rating) {
        String errors;
        try {
            final var trainsData = trainRepository.runQuery4(Integer.parseInt(rating));
            final var file = excelWriter.writeToExcel(excelWriter.generateExcelFileName(DEFAULT_DEPTH), EXCEL_MIME_TYPE, List.of(trainsData));
            return ResponseEntity
                    .ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, file.getContentDispositionHeader())
                    .contentType(MediaType.parseMediaType(file.getContentType()))
                    .body(file.getResource());
        } catch (Exception e) {
            errors = e.getMessage();
            return ResponseEntity.ok(new ByteArrayResource(errors.getBytes()));
        }
    }

    @PostMapping("/query5")
    public ResponseEntity<Resource> query5(@RequestParam String description) {
        String errors;
        try {
            final var tripsData = tripRepository.runQuery5(description)
                    .stream()
                    .map(DataWrapper::new)
                    .toList();
            final var file = excelWriter.writeToExcel(excelWriter.generateExcelFileName(DEFAULT_DEPTH), EXCEL_MIME_TYPE, tripsData);
            return ResponseEntity
                    .ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, file.getContentDispositionHeader())
                    .contentType(MediaType.parseMediaType(file.getContentType()))
                    .body(file.getResource());
        } catch (Exception e) {
            errors = e.getMessage();
            return ResponseEntity.ok(new ByteArrayResource(errors.getBytes()));
        }
    }

    @PostMapping("/query6")
    public ResponseEntity<Resource> query6(@RequestParam String description) {
        String errors;
        try {
            final var countriesData = countryRepository.runQuery6(description)
                    .stream()
                    .map(DataWrapper::new)
                    .toList();
            final var file = excelWriter.writeToExcel(excelWriter.generateExcelFileName(DEFAULT_DEPTH), EXCEL_MIME_TYPE, countriesData);
            return ResponseEntity
                    .ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, file.getContentDispositionHeader())
                    .contentType(MediaType.parseMediaType(file.getContentType()))
                    .body(file.getResource());
        } catch (Exception e) {
            errors = e.getMessage();
            return ResponseEntity.ok(new ByteArrayResource(errors.getBytes()));
        }
    }

    @PostMapping("/query7")
    public ResponseEntity<Resource> query7(@RequestParam String countryName) {
        String errors;
        try {
            final var tripsData = tripRepository.runQuery7(countryName)
                    .stream()
                    .map(DataWrapper::new)
                    .toList();
            final var file = excelWriter.writeToExcel(excelWriter.generateExcelFileName(DEFAULT_DEPTH), EXCEL_MIME_TYPE, tripsData);
            return ResponseEntity
                    .ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, file.getContentDispositionHeader())
                    .contentType(MediaType.parseMediaType(file.getContentType()))
                    .body(file.getResource());
        } catch (Exception e) {
            errors = e.getMessage();
            return ResponseEntity.ok(new ByteArrayResource(errors.getBytes()));
        }
    }

    @PostMapping("/query8")
    public ResponseEntity<Resource> query8(@RequestParam String tripName,
                                           @RequestParam LocalDate departureDate,
                                           @RequestParam LocalDate arrivalDate,
                                           @RequestParam Type type) {
        String errors;
        try {
            final var tripsData = tripRepository.runQuery8(tripName, departureDate.toString(),
                            arrivalDate.toString(), type.name())
                    .stream()
                    .map(DataWrapper::new)
                    .toList();
            final var file = excelWriter.writeToExcel(excelWriter.generateExcelFileName(DEFAULT_DEPTH), EXCEL_MIME_TYPE, tripsData);
            return ResponseEntity
                    .ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, file.getContentDispositionHeader())
                    .contentType(MediaType.parseMediaType(file.getContentType()))
                    .body(file.getResource());
        } catch (Exception e) {
            errors = e.getMessage();
            return ResponseEntity.ok(new ByteArrayResource(errors.getBytes()));
        }
    }

    @PostMapping("/query9")
    public ResponseEntity<Resource> query9(@RequestParam String tripName) {
        String errors;
        try {
            final var tripsData = tripRepository.runQuery9(tripName)
                    .stream()
                    .map(DataWrapper::new)
                    .toList();
            final var file = excelWriter.writeToExcel(excelWriter.generateExcelFileName(DEFAULT_DEPTH), EXCEL_MIME_TYPE, tripsData);
            return ResponseEntity
                    .ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, file.getContentDispositionHeader())
                    .contentType(MediaType.parseMediaType(file.getContentType()))
                    .body(file.getResource());
        } catch (Exception e) {
            errors = e.getMessage();
            return ResponseEntity.ok(new ByteArrayResource(errors.getBytes()));
        }
    }

    @PostMapping("/query10")
    public ResponseEntity<Resource> query10(@RequestParam String tripName) {
        String errors;
        try {
            final var tripsData = tripRepository.runQuery10(tripName)
                    .stream()
                    .map(DataWrapper::new)
                    .toList();
            final var file = excelWriter.writeToExcel(excelWriter.generateExcelFileName(DEFAULT_DEPTH), EXCEL_MIME_TYPE, tripsData);
            return ResponseEntity
                    .ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, file.getContentDispositionHeader())
                    .contentType(MediaType.parseMediaType(file.getContentType()))
                    .body(file.getResource());
        } catch (Exception e) {
            errors = e.getMessage();
            return ResponseEntity.ok(new ByteArrayResource(errors.getBytes()));
        }
    }

    @PostMapping("/query11")
    public ResponseEntity<Resource> query11(@RequestParam String tripName) {
        String errors;
        try {
            final var tripsData = tripRepository.runQueryAdditional11(tripName)
                    .stream()
                    .map(DataWrapper::new)
                    .toList();
            final var file = excelWriter.writeToExcel(excelWriter.generateExcelFileName(DEFAULT_DEPTH), EXCEL_MIME_TYPE, tripsData);
            return ResponseEntity
                    .ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, file.getContentDispositionHeader())
                    .contentType(MediaType.parseMediaType(file.getContentType()))
                    .body(file.getResource());
        } catch (Exception e) {
            errors = e.getMessage();
            return ResponseEntity.ok(new ByteArrayResource(errors.getBytes()));
        }
    }

    @PostMapping("/query12")
    public ResponseEntity<Resource> query12(@RequestParam String tripName) {
        String errors;
        try {
            final var tripsData = tripRepository.runQueryAdditional12(tripName)
                    .stream()
                    .map(DataWrapper::new)
                    .toList();
            final var file = excelWriter.writeToExcel(excelWriter.generateExcelFileName(DEFAULT_DEPTH), EXCEL_MIME_TYPE, tripsData);
            return ResponseEntity
                    .ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, file.getContentDispositionHeader())
                    .contentType(MediaType.parseMediaType(file.getContentType()))
                    .body(file.getResource());
        } catch (Exception e) {
            errors = e.getMessage();
            return ResponseEntity.ok(new ByteArrayResource(errors.getBytes()));
        }
    }

    @PostMapping("/query13")
    public ResponseEntity<Resource> query13(@RequestParam String tripName) {
        String errors;
        try {
            final var tripsData = tripRepository.runQueryAdditional13(tripName)
                    .stream()
                    .map(DataWrapper::new)
                    .toList();
            final var file = excelWriter.writeToExcel(excelWriter.generateExcelFileName(DEFAULT_DEPTH), EXCEL_MIME_TYPE, tripsData);
            return ResponseEntity
                    .ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, file.getContentDispositionHeader())
                    .contentType(MediaType.parseMediaType(file.getContentType()))
                    .body(file.getResource());
        } catch (Exception e) {
            errors = e.getMessage();
            return ResponseEntity.ok(new ByteArrayResource(errors.getBytes()));
        }
    }
}
