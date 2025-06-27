package com.smartclinic.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.smartclinic.model.Doctor;
import com.smartclinic.service.DoctorService;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {
    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping
    public List<Doctor> getAllDoctors() {
        return doctorService.getAllDoctors();
    }
}
