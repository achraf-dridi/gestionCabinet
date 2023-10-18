package com.cabinet.gestion.repositories;

import com.cabinet.gestion.models.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConsultationRepo extends JpaRepository<Consultation, Long> {
    List<Consultation> findByPatientCode(Long codePatient);

}
