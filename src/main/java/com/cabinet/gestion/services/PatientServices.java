package com.cabinet.gestion.services;

import com.cabinet.gestion.models.Patient;
import com.cabinet.gestion.repositories.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServices {

	@Autowired
	private PatientRepo patientRepo;

	public void createPatient(Patient patient) {
		patientRepo.save(patient);
	}

	public List<Patient> getListPatient() {
		return patientRepo.findAll();
	}

	public Patient getPatient(Long code) {
		return patientRepo.findById(code).orElse(null);
	}

	public boolean deletePatient(Long code) {
		// Check if the patient with the given ID exists
		if (patientRepo.existsById(code)) {
			patientRepo.deleteById(code);
			return true;
		}
		return false;
	}

	public boolean updatePatient(Long code, Patient patient) {
		Patient p = patientRepo.findById(code).orElse(null);
		if (patient != null) {
			p.setNom(patient.getNom());
			p.setPrénom(patient.getPrénom());
			p.setAdresse(patient.getAdresse());
			p.setDateNaissance(patient.getDateNaissance());
			p.setSexe(patient.getSexe());
			p.setTéléphone(patient.getTéléphone());
			patientRepo.save(p);
			return true;
		} else {
			return false;
		}
	}

}
