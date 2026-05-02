package com.api.doctorservice.controller;

import com.api.doctorservice.dto.DoctorDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/v1/doctors")
public class DoctorController {

    public List<DoctorDTO> getAllDoctors() {
        return Arrays.asList(
                new DoctorDTO(1L, "Dr. John Doe", "Cardiology"),
                new DoctorDTO(2L, "Dr. Jane Smith", "Neurology"),
                new DoctorDTO(3L, "Dr. Emily Davis", "Pediatrics")
        );
    }

    @GetMapping
    public ResponseEntity<List<DoctorDTO>> getDoctors() {
        List<DoctorDTO> doctors = getAllDoctors();
        return ResponseEntity.ok(doctors);
    }
}
