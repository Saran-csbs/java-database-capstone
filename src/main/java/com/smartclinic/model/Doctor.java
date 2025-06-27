package com.smartclinic.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 * Represents a medical doctor in the Smart Clinic system.
 */
@Entity
@Table(name = "doctors")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                  // Primary key

    private String name;              // Doctor’s full name
    private String speciality;        // Medical specialty

    public Doctor() { /* JPA requires a no-arg constructor */ }

    public Doctor(String name, String speciality) {
        this.name = name;
        this.speciality = speciality;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }
}
