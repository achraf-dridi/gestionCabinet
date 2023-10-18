package com.cabinet.gestion.repositories;

import com.cabinet.gestion.models.Traitement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TraitementRepo extends JpaRepository<Traitement, Long> {
}
