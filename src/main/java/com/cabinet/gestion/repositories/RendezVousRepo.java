package com.cabinet.gestion.repositories;

import com.cabinet.gestion.models.RendezVous;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface RendezVousRepo extends JpaRepository<RendezVous, Long> {
    List<RendezVous> findByPatientCode(Long code);
    List<RendezVous> findRendezVousByDateHeureRendezVousBetween(LocalDateTime dateDebut, LocalDateTime dateFin);
    List<RendezVous> findRendezVousByPatient_Code(Long patient);
}
