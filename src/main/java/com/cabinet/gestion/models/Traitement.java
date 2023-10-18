package com.cabinet.gestion.models;


import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "traitements")
public class Traitement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code;
    private String nom;
    private BigDecimal prix_traitement;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

}
