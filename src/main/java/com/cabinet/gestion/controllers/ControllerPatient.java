package com.cabinet.gestion.controllers;

import com.cabinet.gestion.models.Patient;
import com.cabinet.gestion.services.PatientServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api/patient")
public class ControllerPatient {

    @Autowired
    private PatientServices patientServices;

    @PostMapping(value = "/createpatient")
    public ResponseEntity<String> createPatient(@RequestBody Patient patient) {
        try {
            patientServices.createPatient(patient);
            return new ResponseEntity<>("Object saved successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            // If an exception occurs during the save operation
            String errorMessage = "Error saving object: " + e.getMessage();
            return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/patients")
    public List<Patient> getAllPatient() {
        return patientServices.getListPatient();
    }

    @GetMapping(value = "/patient/{code}")
    @ResponseBody
    public Patient getPatient(@PathVariable Long code) {
        return patientServices.getPatient(code);
    }

    @DeleteMapping(value = "/deletepatient/{code}")
    public void deletePatient(@PathVariable Long code) {
        patientServices.deletePatient(code);
    }

    @PutMapping(value = "updatepatient/{code}")
    @ResponseBody
    public ResponseEntity<String> updatePatient(@PathVariable Long code, @RequestBody Patient patient) {
        if (patientServices.updatePatient(code, patient))
            return ResponseEntity.ok("Patient updated successfully ! ");
        else
            return ResponseEntity.notFound().build();
    }
}
