package com.cabinet.gestion.controllers;

import com.cabinet.gestion.models.RendezVous;
import com.cabinet.gestion.repositories.PatientRepo;
import com.cabinet.gestion.repositories.RendezVousRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Controller
public class ControllerRendezVous {

    @Autowired
    RendezVousRepo rendezVousRepo;

    @Autowired
    PatientRepo patientRepo;

    @PostMapping(value = "/createrendezvous")
    public void createRendezVous(@RequestBody RendezVous rendezVous){
        rendezVousRepo.save(rendezVous);
    }

    @GetMapping(value = "/rendezvous")
    public String rendezVous(Model model) {
        return "calendar.html";
    }


    @GetMapping(value = "/listerendezvous")
    @ResponseBody
    public List<RendezVous> getlisteRendezVous() {
        List<RendezVous> liste = rendezVousRepo.findAll();
        return liste;
    }

    @GetMapping(value = "/findrendezvousbyday")
    public List<RendezVous> findRendezVousByDay(@RequestParam("date")  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        LocalDateTime dateDebut = date.atStartOfDay();
        LocalDateTime datefin = date.atTime(LocalTime.MAX);
        List<RendezVous> liste = rendezVousRepo.findRendezVousByDateHeureRendezVousBetween(dateDebut, datefin);
        return liste;
    }

    @GetMapping(value = "/rendezvousbypatient/{idPatient}")
    public List<RendezVous> findRendezVousByPatientAndDay(@PathVariable Long idPatient) {
        return rendezVousRepo.findRendezVousByPatient_Code(idPatient);
    }

    @DeleteMapping(value = "/deleterendezvous")
    public void deleteRendezVous(@PathVariable Long id ) {
        rendezVousRepo.deleteById(id);
    }
}
