package br.com.sw.planets.service.dto;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.sw.planets.helper.DateHelper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReponseExceptionDTO {
	
	private String message;
	
	private Integer httpStatus;
	
	private String httpMessage;
	
	private String date;
	
	@JsonInclude(Include.NON_NULL)
	private Map<String, Object> violations;
	
	public ReponseExceptionDTO(HttpStatus httpStatus) {
		this.httpMessage = httpStatus.getReasonPhrase();
		this.httpStatus  = httpStatus.value();
		this.date 		 = DateHelper.formatDateAndHour(LocalDateTime.now());
		this.violations  = this.getViolations();
	}
	
	public ReponseExceptionDTO(HttpStatus httpStatus, String message) {
		this.httpMessage = httpStatus.getReasonPhrase();
		this.httpStatus  = httpStatus.value();
		this.message 	 = message;
		this.date 		 = DateHelper.formatDateAndHour(LocalDateTime.now());
		this.violations  = this.getViolations();
	}
	
	public void addViolations(String key, Object value) {
		if (this.violations == null) {
        	this.violations = new HashMap<>();
        }
        this.violations.put(key, value);
    }
}
