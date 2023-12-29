package com.cabinet.gestion.repositories;

import com.cabinet.gestion.models.Traitement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TraitementRepo extends JpaRepository<Traitement, Long> {
	List<Traitement> getTraitementByPatient_Code(Long code);
}
