package com.smartclinic.service;

import java.util.List;
import com.smartclinic.model.Doctor;
import com.smartclinic.repository.DoctorRepository;
import org.springframework.stereotype.Service;

@Service
public class DoctorService {
    private final DoctorRepository repo;

    public DoctorService(DoctorRepository repo) {
        this.repo = repo;
    }

    public List<Doctor> getAllDoctors() {
        return repo.findAll();
    }

    public Doctor getDoctorById(int id) {
        return repo.findById(id).orElseThrow();
    }

    public Doctor saveDoctor(Doctor doc) {
        return repo.save(doc);
    }
}
