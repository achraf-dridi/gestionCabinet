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

	/**
	 *
	 * @param date 		The date from it we want to get list of RendezVous
	 * @return 				List<RendezVous> : List of rendezVous from specific date.
	 */
	public List<RendezVous> getRendezVousByDate(LocalDate date) {
		LocalDateTime dateDebut = date.atStartOfDay();
		LocalDateTime datefin = date.atTime(LocalTime.MAX);
		return rendezvousRepo.findRendezVousByDateHeureRendezVousBetween(dateDebut, datefin);
	}

	/**
	 *
	 * @return List of all RendezVous of today
	 */
	public List<RendezVous> getTodayRendezVous() {
		return getRendezVousByDate(LocalDate.now());
	}

	/**
	 *
	 * @param dateDebut
	 * @return  List of RendezVous from specific hour of the day.
	 */
	public List<RendezVous> getRendezVousRestOfTheDay(LocalDateTime dateDebut) {
		LocalDateTime datefin = dateDebut.toLocalDate().atTime(LocalTime.MAX);
		return rendezvousRepo.findRendezVousByDateHeureRendezVousBetween(dateDebut, datefin);
	}

	/**
	 *
	 * @param code ID of a patient
	 * @return List of RendezVous of specific Patient.
	 */
	public List<RendezVous> getRendezVousByPatient(Long code) {
		return rendezvousRepo.findRendezVousByPatient_Code(code);
	}

	/**
	 *
	 * @param code  Id of a patient
	 * @param date  Specific date of a RendezVous
	 * @return      List of RendezVous for specific Patient in a specific Date.
	 */
	public RendezVous getRendezVousByPatientAndDate(Long code, LocalDate date) {
		LocalDateTime dateDebut = date.atStartOfDay();
		LocalDateTime datefin = date.atTime(LocalTime.MAX);

		return rendezvousRepo.findRendezVousByDateHeureRendezVousBetweenAndPatient_Code(dateDebut, datefin, code);
	}

	/**
	 *
	 * @param code    Id of the RendezVous.
	 * @return 				True if the RendezVous deleted successfully, else false.
	 */
	public Boolean deleteRendezVous(Long code) {
		if (rendezvousRepo.existsById(code)) {
			rendezvousRepo.deleteById(code);
			return true;
		}
		return false;
	}
}
