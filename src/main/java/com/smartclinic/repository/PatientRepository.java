package com.smartclinic.repository;

import com.smartclinic.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

/**
 * Repository for Patient entities.
 * Adds lookup methods by email and phone number.
 */
public interface PatientRepository extends JpaRepository<Patient, Integer> {

    /**
     * Find a patient by their unique email address.
     * @param email the patient’s email
     * @return an Optional containing the Patient if found
     */
    Optional<Patient> findByEmail(String email);

    /**
     * Find a patient by their unique phone number.
     * @param phoneNumber the patient’s phone number
     * @return an Optional containing the Patient if found
     */
    Optional<Patient> findByPhoneNumber(String phoneNumber);
}
