package com.cabinet.gestion.repositories;

import com.cabinet.gestion.models.Meet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface MeetRepository extends JpaRepository<Meet, Long> {
    List<Meet> findMeetByMeetHourBetween(LocalDateTime dateDebut, LocalDateTime dateFin);
    List<Meet> findMeetByPatient_Id(Long PatientId);

    List<Meet> findMeetByMeetHourAfter(LocalDateTime dateDebut);

    Meet findMeetByMeetHourBetweenAndPatient_Id(LocalDateTime dateDebut, LocalDateTime dateFin, Long PatientId);
}
