package com.smartclinic.service;

import com.smartclinic.dto.AvailabilityDto;
import com.smartclinic.model.Appointment;
import com.smartclinic.model.Doctor;
import com.smartclinic.repository.AppointmentRepository;
import com.smartclinic.repository.DoctorRepository;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class DoctorService {

    private final DoctorRepository repo;
    private final AppointmentRepository appointmentRepo;

    public DoctorService(DoctorRepository repo,
                         AppointmentRepository appointmentRepo) {
        this.repo = repo;
        this.appointmentRepo = appointmentRepo;
    }

    /** Retrieve all doctors. */
    public List<Doctor> getAllDoctors() {
        return repo.findAll();
    }

    /** Find a doctor by ID. */
    public Doctor getDoctorById(Long id) {
        return repo.findById(id)
                   .orElseThrow(() -> new IllegalArgumentException(
                       "Doctor not found: " + id));
    }

    /**
     * Returns availability slots (hourly) for the given doctor on the given date.
     */
    public AvailabilityDto getAvailabilityForDate(Long doctorId, LocalDate date) {
        // 1) Build full-day slots from 9AM–5PM
        List<LocalTime> allSlots = IntStream.rangeClosed(9, 17)
            .mapToObj(hour -> LocalTime.of(hour, 0))
            .collect(Collectors.toList());

        // 2) Fetch booked appointments and extract their times
        List<LocalTime> booked = appointmentRepo
            .findByDoctorIdAndAppointmentTimeBetween(
                doctorId,
                date.atStartOfDay(),
                date.atTime(23, 59))
            .stream()
            .map(Appointment::getAppointmentTime)
            .map(LocalTime::from)
            .collect(Collectors.toList());

        // 3) Remove booked slots
        allSlots.removeAll(booked);

        return new AvailabilityDto(doctorId, date, allSlots);
    }

    /**
     * Validates doctor login by email/password.
     * @throws BadCredentialsException if no match.
     */
    public Doctor validateLogin(String email, String rawPassword) {
        Doctor doctor = repo.findByEmail(email)
            .orElseThrow(() -> new BadCredentialsException(
                "Invalid credentials"));
        if (!doctor.getPassword().equals(rawPassword)) {
            throw new BadCredentialsException("Invalid credentials");
        }
        return doctor;
    }

    /** Save or update a doctor. */
    public Doctor saveDoctor(Doctor doc) {
        return repo.save(doc);
    }
}
