package com.cabinet.gestion.controllers;

import com.cabinet.gestion.dto.ApiResponse;
import com.cabinet.gestion.models.Meet;
import com.cabinet.gestion.services.MeetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/meet")
@Slf4j
public class MeetController {
  @Autowired
  MeetService meetService;
  
  @PostMapping(value = "/createmeet")
  @ResponseBody
  @ResponseStatus(HttpStatus.CREATED)
  public ApiResponse<Meet> createMeet(@RequestBody Meet meet){
    try {
      Meet m = meetService.createMeet(meet);
      return ApiResponse.<Meet>builder()
      .message("Meet Created successfully")
      .data(List.of(m))
      .status(HttpStatus.CREATED.value()).build();
    } catch (Exception e) {
      // If an exception occurs during the save operation
      log.error(e.getMessage());
      return ApiResponse.<Meet>builder()
      .message(e.getMessage())
      .data(null)
      .status(HttpStatus.INTERNAL_SERVER_ERROR.value()).build();
    }
  }
  
  @GetMapping(value = "/meets")
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  public ApiResponse<Meet> getMeets() {
    List<Meet> meets = meetService.getMeets();
    if (!meets.isEmpty()) {
      return ApiResponse.<Meet>builder()
      .message("List of meets")
      .data(meets)
      .status(HttpStatus.OK.value()).build();
    } else {
      return ApiResponse.<Meet>builder()
      .message("No meet found")
      .data(null)
      .status(HttpStatus.NO_CONTENT.value()).build();
    }
  }
  
  @GetMapping(value = "/meets_by_date")
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  public ApiResponse<Meet> getMeetsByDate(@RequestParam("date")  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
    List<Meet> meets = meetService.getMeetByDate(date);
    if (!meets.isEmpty()) {
      return ApiResponse.<Meet>builder()
      .message(String.format("List of meets of %s", date))
      .data(meetService.getMeetByDate(date))
      .status(HttpStatus.OK.value()).build();
    } else {
      return ApiResponse.<Meet>builder()
      .message(String.format("No meet found in date %s", date))
      .data(null)
      .status(HttpStatus.NO_CONTENT.value()).build();
    }
  }
  
  @GetMapping(value = "/todaymeet")
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  public ApiResponse<Meet> getTodayMeets() {
    return ApiResponse.<Meet>builder()
    .message("List of today's meetings")
    .data(meetService.getTodayMeets())
    .status(HttpStatus.OK.value()).build();
  }
  
  @GetMapping(value = "/meet_by_patient/{id}")
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  public ApiResponse<Meet> getMeetByPatient(@PathVariable Long patientId) {
    return ApiResponse.<Meet>builder()
    .message("Meetings by patient")
    .data(meetService.getMeetsByPatient(patientId))
    .status(HttpStatus.OK.value()).build();
  }
  
  @GetMapping(value = "/meet_patient_date/{patientId}")
  public ApiResponse<Meet> getMeetByPatientAndDate(@PathVariable Long patientId,
                                            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
    List<Meet> meets = (meetService.getMeetsByPatientAndDate(patientId, date) == null) ? List.of() : List.of(meetService.getMeetsByPatientAndDate(patientId, date));
    if(!meets.isEmpty()) {
      return ApiResponse.<Meet>builder()
      .message("Meetings by patient")
      .data(meets)
      .status(HttpStatus.OK.value()).build();
    } else {
      return ApiResponse.<Meet>builder()
      .message(String.format("No meets found for Patient with ID = %s and date = %s", patientId, date))
      .data(null)
      .status(HttpStatus.NO_CONTENT.value()).build();
    }
  }
  
  @DeleteMapping(value = "/delete/{meetId}")
  public ApiResponse<Meet> deleteMeet(@PathVariable Long meetId) {
    Boolean meetDeleted = meetService.deleteMeet(meetId);
    if (meetDeleted) {
      return ApiResponse.<Meet>builder()
      .message("Meet deleted successfully")
      .data(null)
      .status(HttpStatus.NO_CONTENT.value()).build();
    } else {
      return ApiResponse.<Meet>builder()
      .message("Meet not found")
      .data(null)
      .status(HttpStatus.NOT_FOUND.value()).build();
    }
  }
}
