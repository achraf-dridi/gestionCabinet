package com.cabinet.gestion.services;

import com.cabinet.gestion.models.Traitement;
import com.cabinet.gestion.repositories.TraitementRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TraitementServices {

	@Autowired
	TraitementRepo traitementRepo;

	public void createTraitement(Traitement traitement) {
		traitementRepo.save(traitement);
	}

	public List<Traitement> getListTraitements() {
		return traitementRepo.findAll();
	}

	public Traitement getTraitement(Long code) {
		return traitementRepo.getById(code);
	}

	public List<Traitement> getTraitementsPatient(Long patient) {
		return traitementRepo.getTraitementByPatient_Code(patient);
	}

	public boolean changeTraitement(Long code, Traitement t) {
		Traitement traitement = traitementRepo.findById(code).orElse(null);

		if (traitement!=null) {
			traitement.setConsultations(t.getConsultations());
			traitement.setDescription(t.getDescription());
			traitement.setPatient(t.getPatient());
			traitement.setMontantTotal(t.getMontantTotal());
			traitement.setConsultations(t.getConsultations());
			traitementRepo.save(traitement);
			return true;
		}
		return false;
	}

	public boolean deleteTraitement(Long code) {
		Traitement t = traitementRepo.findById(code).orElse(null);

		if (t!= null) {
			traitementRepo.delete(t);
			return true;
		} else {
			return false;
		}
	}
}
