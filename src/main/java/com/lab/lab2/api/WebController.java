package com.lab.lab2.api;

import com.lab.lab2.repository.CrewMemberRepository;
import com.lab.lab2.repository.CrewRepository;
import com.lab.lab2.repository.TrainRepository;
import com.lab.lab2.services.ExcelWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class WebController {
    private final CrewRepository crewRepository;
    private final CrewMemberRepository crewMemberRepository;
    private final TrainRepository trainRepository;
    private final ExcelWriter excelWriter;
    private static final String EXCEL_MIME_TYPE = "application/vnd.ms-excel";


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
}
