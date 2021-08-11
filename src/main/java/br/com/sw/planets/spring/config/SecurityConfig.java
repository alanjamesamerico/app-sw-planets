package br.com.sw.planets.spring.config;

import static com.google.common.base.Preconditions.checkNotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder.BCryptVersion;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private Environment env;
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
		// AUTHENTICATION CONFIGURATION
	        .authorizeRequests()
//	        	.antMatchers(HttpMethod.GET, "/actuator").permitAll()
//	        	.antMatchers(HttpMethod.DELETE).authenticated()
		    	.anyRequest().authenticated()
		    .and()
		    	.headers()
		    		.frameOptions().sameOrigin()
	        .and()
	        	.httpBasic();
	 }
	 
	@Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/v2/api-docs",
                                   "/configuration/ui",
                                   "/swagger-resources/**",
                                   "/configuration/security",
                                   "/swagger-ui.html",
                                   "/swagger-ui.html",
                                   "/favicon.ico",
                                   "/webjars/**",
                                   "/static/***",
                                   "/static/br/**",
                                   "/resources/**");
	}
        
	 @Override
	 protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		 auth.inMemoryAuthentication()
	      	.withUser(
	      				checkNotNull(env.getProperty("spring.security.user.name"))
	      			)
	      	.password(
	      		this.passwordEncoder().encode(
	      				checkNotNull(env.getProperty("spring.security.user.password")
	      			) 
	      		))
	      	.authorities("ROLE_GAME_SW");
	 }
	 
	 @Bean
	 public PasswordEncoder passwordEncoder() {
		 return new BCryptPasswordEncoder(BCryptVersion.$2A);
	 }
	
}
