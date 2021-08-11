package br.com.sw.planets.service.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StarWarsAPIPlanetDTO {
	
	private String  		name;
	private String 			climate;
	private String  		diameter;
	private String  		gravity;
	private String  		orbital_period;
	private String  		population;
    private String 			rotation_period;
    private String 			surface_water;
    private String 			terrain;
    private String 			url;
    private String 			created;
    private String 			edited;
    private List<String> 	residents;
    private List<String> 	films;
}
