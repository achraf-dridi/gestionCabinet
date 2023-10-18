package com.cabinet.gestion.models;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.math.BigDecimal;
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
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Patient patient;
    private BigDecimal montant_payé;

}
