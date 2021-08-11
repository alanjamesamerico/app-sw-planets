package br.com.sw.planets.service;

import static com.google.common.base.Preconditions.checkNotNull;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.google.common.collect.Iterables;

import br.com.sw.planets.domain.document.PlanetDB;
import br.com.sw.planets.excepton.PlanetDeletionException;
import br.com.sw.planets.excepton.PlanetNotFoundException;
import br.com.sw.planets.excepton.PlanetNotFoundException.Key;
import br.com.sw.planets.excepton.PlanetPersistenceException;
import br.com.sw.planets.helper.DateHelper;
import br.com.sw.planets.repository.PlanetRepository;
import br.com.sw.planets.web.rest.PlanetRestController;
import lombok.extern.log4j.Log4j2;

/**
 * Class responsible for providing services to the class {@link PlanetRestController}
 * 
 */
@Log4j2
@Service
public class PlanetService {
	
	@Autowired
	private PlanetRepository repository;
	
	@Autowired
	private Environment env;
	
	public PlanetDB savePlanet(PlanetDB planet) {
		planet.setCriationDate(DateHelper.formatDateAndHour(LocalDateTime.now()));
		log.info(checkNotNull(env.getProperty("planet.service.info.create.running")));
		PlanetDB planetSaved = this.repository.save(planet);
		if(planetSaved == null) {
			throw new PlanetPersistenceException();
		}
		log.info(env.getProperty("planet.service.info.create.success"));
		return this.repository.save(planet);
	}
	
	public List<PlanetDB> listAllPlanets() {
		
		log.info(checkNotNull(env.getProperty("planet.service.info.findAll.running")));
		
		Iterable<PlanetDB> planets = this.repository.findAll();
		
		if(Iterables.isEmpty(planets)) {
			throw new PlanetNotFoundException();
		}
		
		log.info(env.getProperty("planet.service.info.findAll.success"));
		return (List<PlanetDB>) planets;
	}
	
	public PlanetDB findPlanetById(String id) {
		log.info(checkNotNull(env.getProperty("planet.service.info.findId.running")));
		if(!this.repository.findById(id).isPresent()) {
			throw new PlanetNotFoundException(id, Key.ID);
		}
		log.info(env.getProperty("planet.service.info.findId.success"));
		return this.repository.findById(id).get();
	}
	
	public PlanetDB deletePlanetById(@Valid String id) {
		log.info(checkNotNull(env.getProperty("planet.service.info.delete.running")));
		Optional<PlanetDB> planet = this.repository.findById(id);
		if(planet.isPresent()) {
			this.repository.deleteById(id);
			log.info(env.getProperty("planet.service.info.findId.success "));
			return planet.get();
		} else {
			throw new PlanetDeletionException(id);
		}
	}
	
	public List<PlanetDB> findPlanetByName(@Valid String name) {
		log.info(
			checkNotNull(
					env.getProperty("planet.service.info.findName.running")
			));
		List<PlanetDB> planet = this.repository.findByName(name);
		if(planet.isEmpty()) {
			throw new PlanetNotFoundException(name, Key.NAME);
		}
		return planet;
	}
}
