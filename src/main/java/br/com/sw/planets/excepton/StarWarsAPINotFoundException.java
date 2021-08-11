package br.com.sw.planets.excepton;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class StarWarsAPINotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 4250894092520070049L;
	
	public StarWarsAPINotFoundException(String msg) {
		 super(msg);
		 log.warn("This planet was not found in the SW franchise");
	}
	
	public StarWarsAPINotFoundException() {
		 super();
	}
}
