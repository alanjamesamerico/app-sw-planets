package br.com.sw.planets.web.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.sw.planets.service.dto.StarWarsAPIResultPlanetDTO;

/**
 * Interface responsible for accessing the services of the 
 * official API of the Star Wars franchise
 *
 */
@FeignClient(name = "starsWarsApi", url = "https://swapi.dev/api")	
public interface StarWarsAPIClient {

	/**
	 * Search for a planet by its official name
	 * 
	 * @return result of the official Star Wars API call 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/planets/")
	StarWarsAPIResultPlanetDTO getPlanetByName(@RequestParam final String search);
}
