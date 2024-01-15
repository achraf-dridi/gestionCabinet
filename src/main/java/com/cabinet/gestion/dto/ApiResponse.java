package com.cabinet.gestion.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Component
public class ApiResponse<T> {
	private int status;
	private String message;
	private List<T> data;
	
	
	public static <T> ApiResponse<T> createApiResponse(String message, int status, List<T> data) {
		return new ApiResponse<T>(status, message, data);
	}
}
