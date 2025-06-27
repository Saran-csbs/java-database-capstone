package com.smartclinic.controller;

import com.smartclinic.model.Prescription;
import com.smartclinic.service.AuthService;
import com.smartclinic.service.PrescriptionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

/**
 * Handles prescription-related endpoints.
 */
@RestController
@RequestMapping("/api/prescriptions")
public class PrescriptionController {

    private final PrescriptionService service;
    private final AuthService authService;

    public PrescriptionController(PrescriptionService service, AuthService authService) {
        this.service = service;
        this.authService = authService;
    }

    /**
     * Create a new prescription.
     * URL includes a token path variable for authentication.
     *
     * @param token         authentication token of the doctor
     * @param prescription  prescription details, validated via @Valid
     * @return              the created Prescription or 403 if unauthorized
     */
    @PostMapping("/{token}")
    public ResponseEntity<Prescription> createPrescription(
            @PathVariable String token,
            @Valid @RequestBody Prescription prescription) {

        // Ensure the token is valid and belongs to a doctor
        if (!authService.validateToken(token, "ROLE_DOCTOR")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        Prescription saved = service.create(prescription);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }
}
