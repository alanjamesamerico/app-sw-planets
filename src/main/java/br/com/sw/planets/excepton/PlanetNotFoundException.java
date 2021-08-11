package br.com.sw.planets.excepton;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Getter
public class PlanetNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 2471543538429918951L;

	private Key identifier;
	
	public PlanetNotFoundException(String message, Key identifier) {
		super("No planet found with identifier "
					.concat(identifier.toString()).concat(": ")
					.concat(message));
		log.warn("No planet found. Identifier {}: \"{}\"", identifier, message);
		this.identifier = identifier;
	}
	
	public PlanetNotFoundException() {
		super("No Planet registered in the database!");
		log.warn("No Planet registered in the database!");
	}
	
	public static enum Key {
		ID,
		NAME;
	}
}
