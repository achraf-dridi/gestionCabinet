package com.cabinet.gestion.controllers;

import com.cabinet.gestion.models.Patient;
import com.cabinet.gestion.repositories.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api/patient")
public class ControllerPatient {

    @Autowired
    private PatientRepo patientRepo;

    @PostMapping(value = "/createpatient")
    public String createPatient(@RequestBody Patient patient) {
        patientRepo.save(patient);
        return "Patient created successfully";
    }

    @GetMapping(value = "/patients")
    public List<Patient> getAllPatient() {
        List<Patient> patients = patientRepo.findAll();
        return patients;
    }

    @GetMapping(value = "/patient/{id}")
    @ResponseBody
    public Patient getPatient(@PathVariable Long id) {
        return patientRepo.findById(id).orElse(null);
    }

    @DeleteMapping(value = "/deletepatient/{id}")
    public void deletePatient(@PathVariable Long id) {
        // Check if the patient with the given ID exists
        if (patientRepo.existsById(id)) {
            patientRepo.deleteById(id);
        }
    }

    @PutMapping(value = "updatepatient/{id}")
    @ResponseBody
    public ResponseEntity<String> updatePatient(@PathVariable Long id, @RequestBody Patient patient) {
        Patient p = patientRepo.findById(id).orElse(null);
        if (patient != null) {
            p.setNom(patient.getNom());
            p.setPrénom(patient.getPrénom());
            p.setAdresse(patient.getAdresse());
            p.setDateNaissance(patient.getDateNaissance());
            p.setSexe(patient.getSexe());
            p.setTéléphone(patient.getTéléphone());
            patientRepo.save(p);
            return ResponseEntity.ok("Patient updated successfully ! ");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
