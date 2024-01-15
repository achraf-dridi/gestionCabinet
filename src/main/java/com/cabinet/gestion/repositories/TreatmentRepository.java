package com.cabinet.gestion.repositories;

import com.cabinet.gestion.models.Treatment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TreatmentRepository extends JpaRepository<Treatment, Long> {
	List<Treatment> findTreatmentByPatient_Id(Long PatientId);
}
