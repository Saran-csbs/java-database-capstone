package com.smartclinic.service;

import java.util.List;
import com.smartclinic.model.Appointment;
import com.smartclinic.repository.AppointmentRepository;
import org.springframework.stereotype.Service;

@Service
public class AppointmentService {
    private final AppointmentRepository repository;

    public AppointmentService(AppointmentRepository repository) {
        this.repository = repository;
    }

    public List<Appointment> getAppointmentsByPatientId(int patientId) {
        return repository.findByPatientId(patientId);
    }

    // You can add methods like create, cancel, reschedule etc.
}
