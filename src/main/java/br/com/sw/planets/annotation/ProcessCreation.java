package br.com.sw.planets.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import br.com.sw.planets.aspect.PlanetAspect;

/**
 *	Responsible note to assist the class {@link PlanetAspect} in the requests for creating a Planet
 * 
 */ 
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ProcessCreation {

}
