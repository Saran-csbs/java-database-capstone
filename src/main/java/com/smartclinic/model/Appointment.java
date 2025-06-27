package com.smartclinic.model;

import java.time.LocalDateTime;

public class Appointment {
    private int id;
    private int doctorId;
    private int patientId;
    private LocalDateTime appointmentTime;
    private String status; // Scheduled, Completed, Cancelled

    // Getters, setters, and constructors
}
