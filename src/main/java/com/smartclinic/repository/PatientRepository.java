package com.smartclinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.smartclinic.model.Patient;

public interface PatientRepository extends JpaRepository<Patient, Integer> {
    // e.g., List<Patient> findByLastName(String lastName);
}
