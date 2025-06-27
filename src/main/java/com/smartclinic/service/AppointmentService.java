package com.smartclinic.service;

import com.smartclinic.model.Appointment;
import com.smartclinic.repository.AppointmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AppointmentService {

    private final AppointmentRepository repository;

    public AppointmentService(AppointmentRepository repository) {
        this.repository = repository;
    }

    /** 
     * Retrieves all appointments for a given patient.
     */
    public List<Appointment> getAppointmentsByPatientId(Long patientId) {
        return repository.findByPatientId(patientId);
    }

    /**
     * Books a new appointment by saving it to the database.
     */
    @Transactional
    public Appointment bookAppointment(Appointment appointment) {
        return repository.save(appointment);
    }

    /**
     * Cancels an existing appointment by setting its status.
     */
    @Transactional
    public Appointment cancelAppointment(Long appointmentId) {
        Appointment appt = repository.findById(appointmentId)
            .orElseThrow(() -> new IllegalArgumentException(
                "Appointment not found: " + appointmentId));
        appt.setStatus("Cancelled");
        return repository.save(appt);
    }

    /**
     * (Optional) Reschedules an appointment to a new time.
     */
    @Transactional
    public Appointment rescheduleAppointment(Long appointmentId, 
                                             java.time.LocalDateTime newTime) {
        Appointment appt = repository.findById(appointmentId)
            .orElseThrow(() -> new IllegalArgumentException(
                "Appointment not found: " + appointmentId));
        appt.setAppointmentTime(newTime);
        return repository.save(appt);
    }
}
