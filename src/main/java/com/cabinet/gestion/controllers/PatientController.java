package com.cabinet.gestion.controllers;

import com.cabinet.gestion.dto.ApiResponse;
import com.cabinet.gestion.models.Patient;
import com.cabinet.gestion.services.PatientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api/patient")
@Slf4j
public class PatientController {
  @Autowired
  private PatientService patientService;
  
  @PostMapping(value = "/createpatient")
  @ResponseBody
  @ResponseStatus(HttpStatus.CREATED)
  public ApiResponse<Patient> createPatient(@RequestBody Patient patient) {
    try {
      patientService.createPatient(patient);
      return ApiResponse.<Patient>builder()
      .message(String.format("Patient %s created successfully", patient.getFirstName() + " " + patient.getLastName()))
      .status(HttpStatus.CREATED.value())
      .data(List.of(patient)).build();
    } catch (Exception e) {
      // If an exception occurs during the save operation
      return ApiResponse.<Patient>builder()
      .message(e.getMessage())
      .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
      .data(List.of(patient)).build();
    }
  }

  @GetMapping(value = "/patients")
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  public ApiResponse<Patient> getPatients() {
    List<Patient> patients = patientService.getListPatient();
    return ApiResponse.<Patient>builder()
    .message("")
    .status(HttpStatus.OK.value())
    .data(patients).build();
  }

  @GetMapping(value = "/{patientId}")
  @ResponseBody
  public ApiResponse<Patient> getPatient(@PathVariable Long patientId) {
    return ApiResponse.<Patient>builder()
    .message("")
    .status(HttpStatus.OK.value())
    .data(List.of(patientService.getPatient(patientId))).build();
  }

  @DeleteMapping(value = "/delete/{patientId}")
  @ResponseBody
  public ApiResponse<Patient> deletePatient(@PathVariable Long patientId) {
    try {
      boolean patientDeleted = patientService.deletePatient(patientId);
      if (patientDeleted) {
        return ApiResponse.<Patient>builder()
        .message("Patient deleted successfully")
        .status(HttpStatus.NO_CONTENT.value())
        .data(List.of(Patient.builder().id(patientId).build())).build();
      } else {
        return ApiResponse.<Patient>builder()
        .message("Patient not found")
        .status(HttpStatus.NOT_FOUND.value())
        .data(null).build();
      }
    } catch (Exception e) {
      // Log the exception details
      e.printStackTrace();
      return ApiResponse.<Patient>builder()
      .message(e.getMessage())
      .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
      .data(List.of(Patient.builder().id(patientId).build())).build();
    }
    
  }

  @PutMapping(value = "/update/{patientId}")
  @ResponseBody
  @ResponseStatus(HttpStatus.OK)
  public ApiResponse<Patient> updatePatient(@PathVariable Long patientId, @RequestBody Patient updatedPatient) {
    try {
      if (updatedPatient == null) {
        return ApiResponse.<Patient>builder()
        .message("Updated patient not found")
        .status(HttpStatus.NOT_FOUND.value())
        .data(null)
        .build();
      }
      Patient existingPatient = patientService.getPatient(patientId);
      
      if (existingPatient == null) {
        return ApiResponse.<Patient>builder()
        .message("Patient not found")
        .status(HttpStatus.NOT_FOUND.value())
        .data(null)
        .build();
      }
      patientService.updatePatient(patientId, updatedPatient);
      // Return the updated patient in the response
      return ApiResponse.<Patient>builder()
      .message("Patient updated successfully")
      .status(HttpStatus.OK.value())
      .data(List.of(updatedPatient))
      .build();
    } catch (Exception e) {
      // Log the exception details
      e.printStackTrace();
  
      // If an exception occurs during the update operation
      return ApiResponse.<Patient>builder()
      .message(e.getMessage())
      .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
      .data(null)
      .build();
    }
  }
}
