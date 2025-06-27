package com.smartclinic.controller;

import com.smartclinic.dto.AvailabilityDto;
import com.smartclinic.model.Doctor;
import com.smartclinic.service.AuthService;
import com.smartclinic.service.DoctorService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    private final DoctorService doctorService;
    private final AuthService authService;

    public DoctorController(DoctorService doctorService, AuthService authService) {
        this.doctorService = doctorService;
        this.authService = authService;
    }

    @GetMapping
    public List<Doctor> getAllDoctors() {
        return doctorService.getAllDoctors();
    }

    /**
     * Retrieves availability slots for a given doctor on a specific date.
     * Requires a valid token and allows only Patients to view.
     */
    @GetMapping("/{id}/availability")
    public ResponseEntity<AvailabilityDto> getAvailabilityByDoctor(
            @PathVariable Long id,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestHeader("Authorization") String token) {

        // Validate token and ensure the user has the PATIENT role
        if (!authService.validateToken(token, "ROLE_PATIENT")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        AvailabilityDto availability = doctorService.getAvailabilityForDate(id, date);
        return ResponseEntity.ok(availability);
    }
}
