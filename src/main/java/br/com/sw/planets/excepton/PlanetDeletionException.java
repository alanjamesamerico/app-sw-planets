package br.com.sw.planets.excepton;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class PlanetDeletionException extends RuntimeException {
	
	private static final long serialVersionUID = 668259152861687495L;

	public PlanetDeletionException(String id) {
		super("Planet with id " + id + " could not be deleted because it was not found.");
		log.warn("Planet with id {} could not be deleted because it was not found.");
	}
}
