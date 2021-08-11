package br.com.sw.planets.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Configuration
@ComponentScan(basePackages = {"br.com.b2w.sw.planets"})
public class WebConfig extends WebMvcConfigurationSupport {
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addRedirectViewController("/", "/");
		registry.addRedirectViewController("/csrf", "/csrf");
	    registry.addRedirectViewController("/v2/api-docs", "/v2/api-docs");
	    registry.addRedirectViewController("/actuator", "/actuator");
	    registry.addRedirectViewController("/swagger-resources/configuration/ui", 
	    								   "/swagger-resources/configuration/ui");
	    registry.addRedirectViewController("/swagger-resources/configuration/security", 
	    								   "/swagger-resources/configuration/security");
	    registry.addRedirectViewController("/swagger-resources", "/swagger-resources");
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		log.info("Registering resource handlers . . .");
		registry.addResourceHandler("/**")
					.addResourceLocations("classpath:/static/");
	    registry.addResourceHandler("/swagger-ui.html**")
	    			.addResourceLocations("classpath:/META-INF/resources/swagger-ui.html");
	    registry.addResourceHandler("/webjars/**")
	    			.addResourceLocations("classpath:/META-INF/resources/webjars/");
	}

	
	@Bean
	@Profile("prod")
	public WebMvcConfigurer corsConfigurerEnvironmentProd() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedOrigins(""); 	// Production server URL
			}
		};
	}
	
	@Bean
	@Profile("dev")
	public WebMvcConfigurer corsConfigurerEnvironmentDev() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedOrigins("http://localhost:4200");
			}
		};
	}
}
