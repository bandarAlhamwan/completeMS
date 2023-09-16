package com.gateway.APIGatewayService.config;

import java.net.http.HttpClient;
import java.util.Arrays;
import java.util.Collections;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class AppConfig {

	@Bean
	@LoadBalanced
	public RestTemplate template() {
		return new RestTemplate();
	}
	
	
	@Bean
	CorsWebFilter corsWebFilter() {
	    CorsConfiguration corsConfig = new CorsConfiguration();
	    corsConfig.setAllowedOrigins(Arrays.asList("*"));
	    corsConfig.setMaxAge(8000L);
	    corsConfig.addAllowedMethod("*");
	    corsConfig.addAllowedHeader("*");

	    UrlBasedCorsConfigurationSource source =
	      new UrlBasedCorsConfigurationSource();
	    source.registerCorsConfiguration("/**", corsConfig);

	    return new CorsWebFilter(source);
	}
	
	
	/*
	@Bean
	public CorsWebFilter corsWebFilter() {
	    CorsConfiguration corsConfig = new CorsConfiguration();
	    corsConfig.addAllowedOrigin("*");
	    corsConfig.addAllowedMethod("*");
	    corsConfig.addAllowedHeader("*");
	    //corsConfig.setAllowCredentials(true);

	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    source.registerCorsConfiguration("/**", corsConfig);

	    return new CorsWebFilter(source);
	}
	*/
}
