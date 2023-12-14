package com.cabinet.gestion.services;

import com.cabinet.gestion.models.RendezVous;
import com.cabinet.gestion.repositories.RendezVousRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class RendezVousService {

	@Autowired
	RendezVousRepo rendezvousRepo;

	/**
	 *
	 * @param rendezVous
	 */
	public void createRendezVous(RendezVous rendezVous) {
		rendezvousRepo.save(rendezVous);
	}

	/**
	 *
	 * @return    List of all available rendezVous.
	 */
	public List<RendezVous> getListRendezVous() {
		return rendezvousRepo.findAll();
	}

	public List<RendezVous> getRendezVousByDate(LocalDate date) {
		LocalDateTime dateDebut = date.atStartOfDay();
		LocalDateTime datefin = date.atTime(LocalTime.MAX);
		return rendezvousRepo.findRendezVousByDateHeureRendezVousBetween(dateDebut, datefin);
	}

	public List<RendezVous> getTodayRendezVous() {
		return getRendezVousByDate(LocalDate.now());
	}

	public List<RendezVous> getRendezVousRestOfTheDay(LocalDateTime dateDebut) {
		LocalDateTime datefin = dateDebut.toLocalDate().atTime(LocalTime.MAX);
		return rendezvousRepo.findRendezVousByDateHeureRendezVousBetween(dateDebut, datefin);
	}

	public List<RendezVous> getRendezVousByPatient(Long code) {
		return rendezvousRepo.findRendezVousByPatient_Code(code);
	}

	public RendezVous getRendezVousByPatientAndDate(Long code, LocalDate date) {
		LocalDateTime dateDebut = date.atStartOfDay();
		LocalDateTime datefin = date.atTime(LocalTime.MAX);

		return rendezvousRepo.findRendezVousByDateHeureRendezVousBetweenAndPatient_Code(dateDebut, datefin, code);
	}

	public Boolean deleteRendezVous(Long code) {
		if (rendezvousRepo.existsById(code)) {
			rendezvousRepo.deleteById(code);
			return true;
		}
		return false;
	}
}
