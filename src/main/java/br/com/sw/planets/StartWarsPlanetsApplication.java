package br.com.sw.planets;

import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sw.planets.domain.document.PlanetDB;
import br.com.sw.planets.repository.PlanetRepository;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@EnableFeignClients
@SpringBootApplication
@PropertySource("classpath:log-messages.properties")
public class StartWarsPlanetsApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(StartWarsPlanetsApplication.class);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(StartWarsPlanetsApplication.class, args);
	}
	
	@GetMapping("/")
	public String home() {
		
		return "<h1> Application running successfully! <h1>";
	}
	
	@Bean
    CommandLineRunner initDatabase(PlanetRepository repository) {
        return args -> {
        	
        	log.info("Deleting Planets ...");
        	
        	repository.deleteAll();
        	
        	log.info("Saving Planet ...");
        	
            repository.save(
            	PlanetDB.builder()
            			.terrain("Grasslands")
            			.climate("Temperate")
            			.name("Alderaan")
            			.criationDate(LocalDateTime.now().toString())
            			.build()
            		);
            
            repository.save(
                	PlanetDB.builder()
                			.terrain("Dessert")
                			.climate("Arid")
                			.name("Tatooine")
                			.criationDate(LocalDateTime.now().toString())
                			.build()
                		);
        };
    }
}
