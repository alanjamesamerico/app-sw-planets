package br.com.sw.planets.service;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sw.planets.service.dto.StarWarsAPIPlanetDTO;
import br.com.sw.planets.service.dto.StarWarsAPIResultPlanetDTO;
import br.com.sw.planets.web.client.StarWarsAPIClient;

/**
 * Class responsible for providing services for the official Star Wars API 
 * 
 * @see <a href="https://swapi.dev">API Star Wars</a>
 *
 */
@Service
public class StarWarsAPIService {

	/**
	 * The variable that gives access to the official Star Wars API
	 */
	@Autowired
	private StarWarsAPIClient apiClient;
	
	public Integer getAppearancesFilmByName(String name) {
		return this.getPlanetAPIByNameOneResult(name).get().getFilms().size();
	}
	
	public boolean planetExists(String name) {
		return this.processesExistencePlanet(apiClient.getPlanetByName(name));
	}
	
	public Optional<StarWarsAPIPlanetDTO> getPlanetAPIByNameOneResult(String name) {
		StarWarsAPIResultPlanetDTO searchResult = this.apiClient.getPlanetByName(name);
		if(StringUtils.equals(searchResult.getCount(), "0")) {
			return Optional.empty();
		}
		return Optional.of(searchResult.getResults().get(0));
	}
	
	private boolean processesExistencePlanet(StarWarsAPIResultPlanetDTO result) {
		return StringUtils.equals(result.getCount(), "1") && result.getResults().size() == 1;
	}
	
}
