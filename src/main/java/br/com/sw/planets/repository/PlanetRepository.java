package br.com.sw.planets.repository;

import java.util.List;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import br.com.sw.planets.domain.document.PlanetDB;

/**
 * Interface that provides access to the MongoDB database
 *
 */
@EnableScan
public interface PlanetRepository extends CrudRepository <PlanetDB, String> {

	/**
	 * Searches a planet by name ignoring uppercase and lowercase letters
	 * 
	 */
	List<PlanetDB> findByName(String name);
}
