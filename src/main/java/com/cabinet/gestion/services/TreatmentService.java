package com.cabinet.gestion.services;

import com.cabinet.gestion.dto.TreatmentRequest;
import com.cabinet.gestion.models.Patient;
import com.cabinet.gestion.models.Treatment;
import com.cabinet.gestion.repositories.TreatmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class TreatmentService {

	@Autowired
	TreatmentRepository treatmentRepository;
	
	@Autowired
	PatientService patientService;
	
	

	public Treatment createTreatment(Treatment treatment) {
		Treatment treatment1 = treatmentRepository.save(treatment);
		Patient patient = patientService.getPatient(treatment1.getPatient().getId());
		treatment1.setPatient(patient);
		return treatment1;
	}

	public List<Treatment> getTreatments() {
		return treatmentRepository.findAll();
	}

	public Treatment getTreatment(Long treatmentId) {
		return treatmentRepository.findById(treatmentId).orElse(null);
	}

	public List<Treatment> getTreatmentsByPatient(Long patientId) {
		return treatmentRepository.findTreatmentByPatient_Id(patientId);
	}

	public void updateTreatment(Long treatmentId, Treatment newTreatment) {
		Treatment traitement = treatmentRepository.findById(treatmentId).orElse(null);

		if (traitement!=null) {
			assert newTreatment != null;
			Patient patient = patientService.getPatient(newTreatment.getPatient().getId());
			traitement.setConsultations(newTreatment.getConsultations());
			traitement.setDescription(newTreatment.getDescription());
			traitement.setPatient(patient);
			traitement.setTreatmentPrice(newTreatment.getTreatmentPrice());
			treatmentRepository.save(traitement);
		}
	}

	public boolean deleteTreatment(Long treatmentId) {
		Treatment t = treatmentRepository.findById(treatmentId).orElse(null);

		if (t!= null) {
			treatmentRepository.delete(t);
			return true;
		} else {
			return false;
		}
	}
	
	public Treatment mapToTreatment(TreatmentRequest treatmentRequest) {
		Patient patient = patientService.getPatient(treatmentRequest.getPatientId());
		return Treatment.builder()
		.description(treatmentRequest.getDescription())
		.treatmentPrice(treatmentRequest.getTreatmentPrice())
		.consultations(null).patient(patient).build();
	}
}
