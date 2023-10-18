package com.cabinet.gestion.repositories;

import com.cabinet.gestion.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepo extends JpaRepository<Patient, Long> {
}
