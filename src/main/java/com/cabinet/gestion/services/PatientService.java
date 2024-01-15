package com.cabinet.gestion.services;

import com.cabinet.gestion.models.Patient;
import com.cabinet.gestion.repositories.PatientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PatientService {

	@Autowired
	private PatientRepository patientRepository;

	public void createPatient(Patient patient) {
		patientRepository.save(patient);
	}

	public List<Patient> getListPatient() {
		return patientRepository.findAll();
	}

	public Patient getPatient(Long code) {
		return patientRepository.findById(code).orElse(null);
	}

	public boolean deletePatient(Long code) {
		// Check if the patient with the given ID exists
		if (patientRepository.existsById(code)) {
			patientRepository.deleteById(code);
			return true;
		}
		return false;
	}

	public boolean updatePatient(Long patientId, Patient newPatient) {
		Patient p = patientRepository.findById(patientId).orElse(null);
		if (newPatient != null) {
			assert p != null;
			p.setFirstName(newPatient.getFirstName());
			p.setLastName(newPatient.getLastName());
			p.setAddress(newPatient.getAddress());
			p.setBirthday(newPatient.getBirthday());
			p.setGender(newPatient.getGender());
			p.setPhone(newPatient.getPhone());
			patientRepository.save(p);
			return true;
		} else {
			return false;
		}
	}
}
