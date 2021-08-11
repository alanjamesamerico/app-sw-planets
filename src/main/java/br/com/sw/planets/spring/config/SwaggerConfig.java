package br.com.sw.planets.spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RestController;

import static com.google.common.base.Preconditions.checkNotNull;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@PropertySource("classpath:swagger-message.properties")
public class SwaggerConfig {
	
	@Autowired
	private Environment env;
	
	@Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()                  
          .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))    
          .paths(PathSelectors.any())
          .build()
          .apiInfo(this.appInfo());                                           
    }
	
	public ApiInfo appInfo() {
    	return new ApiInfoBuilder()
    		.title(
    				checkNotNull(env.getProperty("swagger.info.title")))
    		.description(
    				checkNotNull(env.getProperty("swagger.info.desc")))
    		.version(
    				checkNotNull(env.getProperty("swagger.info.desc")))
    		.build();
    }
}
