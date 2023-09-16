package com.gateway.APIGatewayService.filter;

import java.util.List;
import java.util.function.Predicate;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

@Component
public class RouteValidator {

	// using: when ever do the validate token // to check the token is part of
	// header or not

	// to ignore check header token
	public static final List<String> openApiEndpoints = List.of("securityApp/userCredential", "securityApp/login");

	public Predicate<ServerHttpRequest> isSecured = request -> openApiEndpoints.stream()
			.noneMatch(uri -> request.getURI().getPath().contains(uri));
}
