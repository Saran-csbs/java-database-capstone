package com.smartclinic.controller;

import org.springframework.web.bind.annotation.*;
import com.smartclinic.model.Prescription;
import com.smartclinic.service.PrescriptionService;
import java.util.List;

@RestController
@RequestMapping("/api/prescriptions")
public class PrescriptionController {
    private final PrescriptionService service;

    public PrescriptionController(PrescriptionService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public Prescription getById(@PathVariable String id) {
        return service.getById(id);
    }

    @PostMapping
    public Prescription create(@RequestBody Prescription presc) {
        return service.create(presc);
    }
}
