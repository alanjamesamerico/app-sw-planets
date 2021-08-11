package br.com.sw.planets.web.rest;

import static com.google.common.base.Preconditions.checkNotNull;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.sw.planets.domain.document.PlanetDB;
import br.com.sw.planets.service.PlanetService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.log4j.Log4j2;

/**
 * Class responsible for providing access to the Star Wars Planet API services
 *
 */
@Log4j2
@RestController
@RequestMapping(path = "/planets")
@Api(tags = { "Planet Rest Controller" })
public class PlanetRestController {

	@Autowired
	private PlanetService service;

	@Autowired
	private Environment env;
	
	@PostMapping
//	@ProcessCreation
	@ApiOperation(value = "Service that allows the creation of a planet")
	@ApiResponses(value = { 
			@ApiResponse(code = 201, message = "Planet created"),
			@ApiResponse(code = 202, message = "Access accepted") })
	public ResponseEntity<?> save(@RequestBody @Valid final PlanetDB planet) {
		log.info(checkNotNull((env.getProperty("planet.rest.info.save.access"))));
		PlanetDB planetSaved = this.service.savePlanet(planet);
		if(planetSaved != null) {
			URI location = ServletUriComponentsBuilder
							.fromCurrentRequest()
								.path("/{id}")
								.buildAndExpand(planetSaved.getId())
								.toUri();
			return ResponseEntity.created(location).build();
		} else {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Exception());
		}
	}
	
	@GetMapping
	@ApiOperation(value = "Service that allows you to search for all the planets")
	@ApiResponses(value = { 
		@ApiResponse(code = 200, message = "Found planets"),
		@ApiResponse(code = 404, message = "No planet was found") })
	public ResponseEntity<?> listAll() {
		log.info(env.getProperty("planet.rest.info.findAll.access"));
		return ResponseEntity.ok().body(this.service.listAllPlanets());
	}
	
	@GetMapping("/{id}")
	@ApiOperation(value = "Service that allows you to search for a planet by id")
	public ResponseEntity<?> findById(@PathVariable @Valid final String id) {
		log.info(checkNotNull(env.getProperty("planet.rest.info.findId.access")));
		return ResponseEntity.ok().body(this.service.findPlanetById(id));
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation(value = "Service that allows you to delete a planet by id")
	public ResponseEntity<?> deleteById(@PathVariable @Valid final String id) {
		log.info(checkNotNull(env.getProperty("planet.rest.info.delete.access")));
		return ResponseEntity.ok(this.service.deletePlanetById(id));
	}
	
	@GetMapping("/name/{name}")
	@ApiOperation(value = "Service that allows you to search for a planet by name")
	public ResponseEntity<?> findByName(@PathVariable @Valid final String name) {
		log.info(checkNotNull(env.getProperty("planet.rest.info.findName.access")));
		return ResponseEntity.ok().body(this.service.findPlanetByName(name));
	}
}
