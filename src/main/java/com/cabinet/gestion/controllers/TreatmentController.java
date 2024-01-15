package com.cabinet.gestion.controllers;

import com.cabinet.gestion.dto.ApiResponse;
import com.cabinet.gestion.models.Treatment;
import com.cabinet.gestion.services.TreatmentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/treatment")
public class TreatmentController {

	@Autowired
	TreatmentService treatmentService;
	@PostMapping(value= "/createtreatment")
	@ResponseStatus(HttpStatus.CREATED)
	public  ApiResponse<Treatment> createTreatment(@RequestBody Treatment treatment) {
		try {
			Treatment t = treatmentService.createTreatment(treatment);
			return ApiResponse.<Treatment>builder()
			.message("Treatment saved successfully")
			.status(HttpStatus.CREATED.value())
			.data(List.of(t)).build();
		} catch (Exception e) {
			// If an exception occurs during the save operation
			return ApiResponse.<Treatment>builder()
			.message(e.getMessage())
			.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
			.data(List.of(treatment)).build();
		}
	}

	@GetMapping(value = "/treatments")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ApiResponse<Treatment> getTreatments() {
		List<Treatment> treatments = treatmentService.getTreatments();
		if (!treatments.isEmpty()) {
			return ApiResponse.<Treatment>builder()
			.message("List of treatments")
			.status(HttpStatus.OK.value())
			.data(treatments).build();
		} else {
			return ApiResponse.<Treatment>builder()
			.message("No treatment found")
			.status(HttpStatus.NO_CONTENT.value())
			.data(treatments).build();
		}
	}

	@GetMapping(value = "/{treatmentId}")
	@ResponseStatus(HttpStatus.OK)
	public ApiResponse<Treatment> getTreatment(@PathVariable Long treatmentId) {
		try {
			Treatment treatment = treatmentService.getTreatment(treatmentId);
			if (treatment != null) {
				return ApiResponse.<Treatment>builder()
				.message("")
				.status(HttpStatus.OK.value())
				.data(List.of(treatment)).build();
			} else {
				return ApiResponse.<Treatment>builder()
				.message(String.format("No treatment found with ID = %s", treatmentId.toString()))
				.status(HttpStatus.NO_CONTENT.value())
				.data(null).build();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ApiResponse.<Treatment>builder()
			.message(e.getMessage())
			.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
			.data(null).build();
		}
	}

	@GetMapping(value = "/patient/{patientId}")
	public ApiResponse<Treatment> getTreatmentsPatient(@PathVariable Long patientId) {
		List<Treatment> treatments = treatmentService.getTreatmentsByPatient(patientId);
		if (!treatments.isEmpty()) {
			return ApiResponse.<Treatment>builder()
			.message("")
			.status(HttpStatus.OK.value())
			.data(treatments).build();
		} else {
			return ApiResponse.<Treatment>builder()
			.message(String.format("No treatment found for the patient with ID = %s", patientId.toString()))
			.status(HttpStatus.NO_CONTENT.value())
			.data(null).build();
		}
	}

	@PutMapping("/update/{treatmentId}")
	public ApiResponse<Treatment> updateTreatment(@PathVariable Long treatmentId, Treatment updatedTreatment) {
		try {
			if (updatedTreatment == null) {
				return ApiResponse.<Treatment>builder()
				.message("Updated treatment not found")
				.status(HttpStatus.NOT_FOUND.value())
				.data(null)
				.build();
			}
			Treatment treatment = treatmentService.getTreatment(treatmentId);
			
			if ( treatment == null) {
				return ApiResponse.<Treatment>builder()
				.message(String.format("Treatment with ID = %s not found", treatmentId.toString()))
				.status(HttpStatus.NOT_FOUND.value())
				.data(null)
				.build();
			}
			treatmentService.updateTreatment(treatmentId, updatedTreatment);
			// Return the updated patient in the response
			return ApiResponse.<Treatment>builder()
			.message("Treatment updated successfully")
			.status(HttpStatus.OK.value())
			.data(List.of(updatedTreatment))
			.build();
		} catch (Exception e) {
			// Log the exception details
			e.printStackTrace();
			// If an exception occurs during the update operation
			return ApiResponse.<Treatment>builder()
			.message(e.getMessage())
			.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
			.data(null)
			.build();
		}
	}

	@DeleteMapping(value = "/delete/{treatmentId}")
	@ResponseBody
	public ApiResponse<Treatment> deleteTreatment(@PathVariable Long treatmentId) {
		boolean deletionSuccessful = treatmentService.deleteTreatment(treatmentId);
		if (deletionSuccessful) {
			return ApiResponse.<Treatment>builder()
			.message("Patient deleted successfully")
			.status(HttpStatus.NO_CONTENT.value())
			.data(List.of(Treatment.builder().id(treatmentId).build())).build();
		} else {
			return ApiResponse.<Treatment>builder()
			.message("Treatment not found")
			.status(HttpStatus.NOT_FOUND.value())
			.data(null).build();
		}
	}
}
