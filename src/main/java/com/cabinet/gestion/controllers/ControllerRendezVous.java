package com.cabinet.gestion.controllers;

import com.cabinet.gestion.models.RendezVous;
import com.cabinet.gestion.repositories.PatientRepo;
import com.cabinet.gestion.repositories.RendezVousRepo;
import com.cabinet.gestion.services.RendezVousService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/rendezvous")

public class ControllerRendezVous {

    @Autowired
    RendezVousService rendezVousService;

    @Autowired
    PatientRepo patientRepo;

    @PostMapping(value = "/createrendezvous")
    public void createRendezVous(@RequestBody RendezVous rendezVous){
      rendezVousService.createRendezVous(rendezVous);
    }

    @GetMapping(value = "/listRendezvous")
    @ResponseBody
    public List<RendezVous> getListeRendezVous() {
      return rendezVousService.getListRendezVous();
    }

    @GetMapping(value = "/rendezvous_by_date")
    public List<RendezVous> getRendezVousByDate(@RequestParam("date")  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return rendezVousService.getRendezVousByDate(date);
    }

    @GetMapping(value = "/rendezvous_today")
    public List<RendezVous> getTodayRendezVous() {
        return rendezVousService.getTodayRendezVous();
    }

    @GetMapping(value = "/rendezvous_by_patient/{id}")
    public List<RendezVous> getRendezVousByPatient(@PathVariable Long id) {
      return rendezVousService.getRendezVousByPatient(id);
    }

    @GetMapping(value = "/rendezvous_patient_date/{codePatient}")
    public RendezVous getRendezVousByPatientAndDate(@PathVariable Long patientCode,
                                                    @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
      return rendezVousService.getRendezVousByPatientAndDate(patientCode, date);
    }

    @DeleteMapping(value = "/deleterendezvous/{id}")
    public ResponseEntity<String> deleteRendezVous(@PathVariable Long id) {
        Boolean deleted = rendezVousService.deleteRendezVous(id);
        if (deleted) return ResponseEntity.ok("Rendez vous deleted successfully ");
        else return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur when deleting this rendezVous !");
    }
}
