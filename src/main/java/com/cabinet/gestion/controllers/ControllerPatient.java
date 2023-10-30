package com.cabinet.gestion.controllers;

import com.cabinet.gestion.models.Patient;
import com.cabinet.gestion.repositories.PatientRepo;
import com.cabinet.gestion.repositories.RendezVousRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Controller()
public class ControllerPatient {

    @Autowired
    private PatientRepo patientRepo;

    @Autowired
    private RendezVousRepo rendezVousRepo;

    @RequestMapping(value = "/createpatient", method = RequestMethod.POST)
    public String createPatient(@RequestBody Patient patient) {
        patientRepo.save(patient);
        return "Patient saved successfully...";
    }

    @GetMapping(value = {"", "/", "/home"})
    public String getHomePage(Model model) {
        List<Patient> patients = patientRepo.findAll();
        Long nombrePatients = patientRepo.count();
        Long nombreRendezVous = (long) rendezVousRepo.findRendezVousByDateHeureRendezVousBetween(LocalDate.now().atStartOfDay(), LocalDate.now().atTime(LocalTime.MAX)).size();
        model.addAttribute("patients", patients);
        model.addAttribute("nombrePatients", nombrePatients);
        model.addAttribute("nombreRendezVous", nombreRendezVous);
        return "acceuil.html";
    }

    @GetMapping(value = "/patients")
    public String getAllPatient(Model model) {
        List<Patient> patients = patientRepo.findAll();
        model.addAttribute("patients", patients);
        return "patients.html";
    }

    @GetMapping(value = "/patient/{id}")
    @ResponseBody
    public Patient getPatient(@PathVariable Long id) {
        return patientRepo.findById(id).orElse(null);
    }

    @DeleteMapping(value = "/deletepatient/{id}")
    public String deletePatient(@PathVariable Long id, Model model) {
        // Check if the patient with the given ID exists
        if (patientRepo.existsById(id)) {
            patientRepo.deleteById(id);
        }

        // Fetch the updated patient list
        List<Patient> updatedPatients = patientRepo.findAll();

        // Update the patient list in the model
        model.addAttribute("patients", updatedPatients);

        return "redirect:/patients";
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
