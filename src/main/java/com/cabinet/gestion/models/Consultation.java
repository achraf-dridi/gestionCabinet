package com.cabinet.gestion.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "consultations")
public class Consultation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date consultationDate;
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;
    @ManyToOne
    @JoinColumn(name = "traitement_id")
    private Treatment treatment;
    @Lob
    private String diagnostic;
    @Lob
    private String medicalPrescription;
    private LocalDateTime hour;

    @OneToOne
    @JoinColumn(name = "meet_id")
    private Meet meet;
    private String notes;
    private BigDecimal paidAmount;

}
