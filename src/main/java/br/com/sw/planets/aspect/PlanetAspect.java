package br.com.sw.planets.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import br.com.sw.planets.domain.document.PlanetDB;
import br.com.sw.planets.excepton.StarWarsAPINotFoundException;
import br.com.sw.planets.service.StarWarsAPIService;
import lombok.extern.log4j.Log4j2;


@Log4j2
@Aspect
@Component
public class PlanetAspect {
	
	@Autowired
	private StarWarsAPIService apiService;
	
	@Autowired
	private Environment env;
	
	@Around("@annotation(br.com.sw.planets.annotation.ProcessCreation)")
	public Object processPlanetName(ProceedingJoinPoint joinPoint) throws Throwable {
		
		log.info("Intercepting the request via Aspect");
		
		final PlanetDB planet = (PlanetDB) joinPoint.getArgs()[0];
		
		if(this.apiService.planetExists(planet.getName())) {
			planet.setAppearances(
					this.apiService.getAppearancesFilmByName(planet.getName())
			);
			return joinPoint.proceed(joinPoint.getArgs());
		}
		throw 
			new StarWarsAPINotFoundException(
					this.env.getProperty("exception.api-sw.not-found.warn"));
	}
}
