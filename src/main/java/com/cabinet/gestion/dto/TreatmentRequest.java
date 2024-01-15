package com.cabinet.gestion.dto;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Getter
@Setter
public class TreatmentRequest {
	
	private String description;
	private BigDecimal treatmentPrice;
	private Long patientId;
}
