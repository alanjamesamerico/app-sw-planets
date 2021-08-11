package br.com.sw.planets.service.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StarWarsAPIResultPlanetDTO {
	
	private String count; 
	private String next; 
	private String previous; 
	private List<StarWarsAPIPlanetDTO> results;
}
