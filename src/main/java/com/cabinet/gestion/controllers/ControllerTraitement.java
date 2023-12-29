package com.cabinet.gestion.controllers;

import com.cabinet.gestion.models.Traitement;
import com.cabinet.gestion.services.TraitementServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/traitement/api")
public class ControllerTraitement {

	@Autowired
	TraitementServices traitementServices;
	@PostMapping(value= "/createtraitement")
	public  ResponseEntity<String> createTraitement(@RequestBody Traitement traitement) {
		try {
			traitementServices.createTraitement(traitement);
			return new ResponseEntity<>("Traitement saved successfully", HttpStatus.CREATED);
		} catch (Exception e) {
			// If an exception occurs during the save operation
			String errorMessage = "Error saving traitement: " + e.getMessage();
			return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = "/traitements")
	public ResponseEntity<List<Traitement>> getTraitements() {
		List<Traitement> traitements = traitementServices.getListTraitements();
		if (!traitements.isEmpty()) {
			return ResponseEntity.ok(traitements);  // HttpStatus.OK
		} else {
			return ResponseEntity.noContent().build();  // HttpStatus.NO_CONTENT
		}
	}

	@GetMapping(value = "/traitement/{code}")
	public ResponseEntity<Traitement> getTraitement(@PathVariable Long code) {
		Traitement t = traitementServices.getTraitement(code);
		if (t != null) {
			return ResponseEntity.ok(t);  // HttpStatus.OK
		} else {
			return ResponseEntity.notFound().build();  // HttpStatus.NOT_FOUND
		}
	}

	@GetMapping(value = "/traitement/{patient}")
	public ResponseEntity<List<Traitement>> getTraitementsPatient(@PathVariable Long patient) {
		List<Traitement> t = traitementServices.getTraitementsPatient(patient);
		if (t != null) {
			return ResponseEntity.ok(t);  // HttpStatus.OK
		} else {
			return ResponseEntity.notFound().build();  // HttpStatus.NOT_FOUND
		}
	}

	@PutMapping("/changetaitement/{code}")
	public ResponseEntity<Traitement> changeTraitement(@PathVariable Long code, Traitement traitement) {
		boolean traitementUpdated = traitementServices.changeTraitement(code, traitement);

		if (traitementUpdated) return ResponseEntity.ok(traitement);
		else return ResponseEntity.notFound().build();
	}

	@DeleteMapping(value = "/traitement/{code}")
	public ResponseEntity<String> deleteTraitement(@PathVariable Long code) {
		boolean deletionSuccessful = traitementServices.deleteTraitement(code); // Replace with actual deletion logic

		if (deletionSuccessful) {
			return new ResponseEntity<>("Object deleted successfully", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Object not found or could not be deleted", HttpStatus.NOT_FOUND);
		}
	}
}
