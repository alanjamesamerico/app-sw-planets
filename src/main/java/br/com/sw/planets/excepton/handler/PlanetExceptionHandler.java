package br.com.sw.planets.excepton.handler;

import static com.google.common.base.Preconditions.checkNotNull;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.sw.planets.excepton.PlanetDeletionException;
import br.com.sw.planets.excepton.PlanetNotFoundException;
import br.com.sw.planets.excepton.PlanetPersistenceException;
import br.com.sw.planets.excepton.StarWarsAPINotFoundException;
import br.com.sw.planets.helper.DateHelper;
import br.com.sw.planets.service.dto.ReponseExceptionDTO;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestControllerAdvice
public class PlanetExceptionHandler {
	
	@Autowired
	private Environment env;
	
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	@ExceptionHandler(PlanetNotFoundException.class)
	public ReponseExceptionDTO planetNotFoundHandler(PlanetNotFoundException exception) {
		return exception.getIdentifier().equals(null) 								?
			new ReponseExceptionDTO(HttpStatus.NOT_FOUND, exception.getMessage())	:
			new ReponseExceptionDTO(HttpStatus.NOT_FOUND, exception.getMessage());
	}
	
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(PlanetPersistenceException.class)
	public ReponseExceptionDTO planetPersistenceHandler(PlanetPersistenceException exception) {
		return new ReponseExceptionDTO(HttpStatus.INTERNAL_SERVER_ERROR, 
										"Error when trying to save the Planet");
	}
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(StarWarsAPINotFoundException.class)
	public ReponseExceptionDTO starWarsAPINotFoundPlanetHandler(
											StarWarsAPINotFoundException exception) {
		return new ReponseExceptionDTO(HttpStatus.BAD_REQUEST, exception.getMessage());
	}
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(PlanetDeletionException.class)
	public ReponseExceptionDTO planetDeleteExceptionHandler(
			PlanetDeletionException exception) {
		return new ReponseExceptionDTO(HttpStatus.BAD_REQUEST, exception.getMessage());
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ReponseExceptionDTO NonValidatedArgumentHandler(
									MethodArgumentNotValidException exception) {
		
		log.error(checkNotNull(this.env.getProperty("exception.api-sw.validation.error")));

		ReponseExceptionDTO error = new ReponseExceptionDTO(); 
		error.setMessage(checkNotNull(this.env.getProperty("exception.api-sw.validation.error")));
		error.setDate(DateHelper.formatDateAndHour(LocalDateTime.now()));
		error.setHttpStatus(HttpStatus.BAD_REQUEST.value());
		error.setHttpMessage(HttpStatus.BAD_REQUEST.getReasonPhrase());
		
		exception.getBindingResult().getGlobalErrors().forEach(
				e -> error.addViolations(e.getObjectName(), e.getDefaultMessage())
			);
		
		exception.getBindingResult().getFieldErrors()
			.forEach(e -> error.addViolations(e.getField(), e.getDefaultMessage()));
		
		return error;
	}
}
