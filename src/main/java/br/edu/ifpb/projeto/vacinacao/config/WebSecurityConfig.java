package br.edu.ifpb.projeto.vacinacao.config;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	    http.cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues())
	    .and().csrf().disable().exceptionHandling();
	}
	
	
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
	    CorsConfiguration configuration = new CorsConfiguration();
	    
	    configuration.setAllowedOrigins(Collections.singletonList("*"));
	    configuration.setAllowedMethods(Collections.singletonList("*"));
	    configuration.setAllowedHeaders(Collections.singletonList("*"));
	    configuration.setExposedHeaders(Arrays.asList("x-total-pages","x-total-pages"));
	    configuration.setAllowCredentials(true);
	    
	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    
	    source.registerCorsConfiguration("/**", configuration);
	    return source;
	}
}