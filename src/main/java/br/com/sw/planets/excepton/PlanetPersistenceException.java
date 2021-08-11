package br.com.sw.planets.excepton;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class PlanetPersistenceException extends RuntimeException {

	private static final long serialVersionUID = -4281914989015746152L;
	
	public PlanetPersistenceException() {
		super("It was not possible to create the planet");
		log.warn("The planet was not created");
	}
}
