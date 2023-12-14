package com.cabinet.gestion.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "traitements")
public class Traitement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code;

    private String description;

    private BigDecimal montantTotal;

    @OneToMany
    private List<Consultation> consultations;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

}
