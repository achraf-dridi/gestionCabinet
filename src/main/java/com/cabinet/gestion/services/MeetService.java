package com.cabinet.gestion.services;

import com.cabinet.gestion.models.Meet;
import com.cabinet.gestion.models.Patient;
import com.cabinet.gestion.repositories.MeetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class MeetService {

	@Autowired
	MeetRepository meetRepository;
	
	@Autowired
	PatientService patientService;

	/**
	 *
	 * @param meet
	 */
	public Meet createMeet(Meet meet) {
		Meet meet1 = meetRepository.save(meet);
		Patient patient = patientService.getPatient(meet.getPatient().getId());
		meet1.setPatient(patient);
		return meet1;
	}

	/**
	 *
	 * @return    List of all available rendezVous.
	 */
	public List<Meet> getMeets() {
		return meetRepository.findAll();
	}

	/**
	 *
	 * @param date 		The date from it we want to get list of Meets.
	 * @return 				List<Meet> : List of Meets from specific date.
	 */
	public List<Meet> getMeetByDate(LocalDate date) {
		LocalDateTime dateDebut = date.atStartOfDay();
		LocalDateTime datefin = date.atTime(LocalTime.MAX);
		return meetRepository.findMeetByMeetHourBetween(dateDebut, datefin);
	}

	/**
	 *
	 * @return List of all meets of today
	 */
	public List<Meet> getTodayMeets() {
		return getMeetByDate(LocalDate.now());
	}

	/**
	 *
	 * @param startTime
	 * @return  List of RendezVous from specific hour of the day.
	 */
	public List<Meet> getMeetsFromStartTime(LocalDateTime startTime) {
		LocalDateTime endTime = startTime.toLocalDate().atTime(LocalTime.MAX);
		return meetRepository.findMeetByMeetHourBetween(startTime, endTime);
	}

	/**
	 *
	 * @param patientId ID of a patient
	 * @return List of Meets of specific Patient.
	 */
	public List<Meet> getMeetsByPatient(Long patientId) {
		return meetRepository.findMeetByPatient_Id(patientId);
	}

	/**
	 *
	 * @param patientId  Id of a patient
	 * @param date  Specific date of a Meet
	 * @return      List of RendezVous for specific Patient in a specific Date.
	 */
	public Meet getMeetsByPatientAndDate(Long patientId, LocalDate date) {
		LocalDateTime dateDebut = date.atStartOfDay();
		LocalDateTime datefin = date.atTime(LocalTime.MAX);

		return meetRepository.findMeetByMeetHourBetweenAndPatient_Id(dateDebut, datefin, patientId);
	}

	/**
	 *
	 * @param meetId    Id of the RendezVous.
	 * @return 				True if the RendezVous deleted successfully, else false.
	 */
	public Boolean deleteMeet(Long meetId) {
		if (meetRepository.existsById(meetId)) {
			meetRepository.deleteById(meetId);
			return true;
		}
		return false;
	}
}
