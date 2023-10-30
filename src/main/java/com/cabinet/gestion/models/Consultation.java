package com.cabinet.gestion.models;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "consultations")
public class Consultation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code;

    private Date dateConsultation;
    @ManyToOne
    @JoinColumn(name = "patient_code")
    private Patient patient;

    @ManyToOne
    private Traitement traitement;

    @Lob
    private String diagnostic;

    @Lob
    private String prescriptionMedicales;

    private LocalDateTime heure;

    @OneToOne
    @JoinColumn(name = "rendez_vous_id")
    private RendezVous rendezVous;

    private String notes;

    private BigDecimal montant_payé;

}
