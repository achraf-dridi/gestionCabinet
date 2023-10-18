package com.cabinet.gestion.models;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "rendez_vous")
public class RendezVous {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime dateHeureRendezVous;
    @ManyToOne
    @JoinColumn(name = "patient_code")
    private Patient patient;

    public RendezVous(Long codeRendezVous, LocalDateTime dateHeureRendezVous, Patient patient) {
        this.id = codeRendezVous;
        this.dateHeureRendezVous = dateHeureRendezVous;
        this.patient = patient;
    }

    public RendezVous() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long codeRendezVous) {
        this.id = codeRendezVous;
    }

    public LocalDateTime getDateHeureRendezVous() {
        return dateHeureRendezVous;
    }

    public void setDateHeureRendezVous(LocalDateTime dateHeureRendezVous) {
        this.dateHeureRendezVous = dateHeureRendezVous;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
