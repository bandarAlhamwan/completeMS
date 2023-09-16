package com.gateway.APIGatewayService.filter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.client.RestTemplate;

import com.gateway.APIGatewayService.Role.Role;
import com.gateway.APIGatewayService.Role.RoleRepository;
import com.gateway.APIGatewayService.exception.UnauthorisedException;
import com.gateway.APIGatewayService.util.JwtUtil;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.shared.Application;

 
@Component
@CrossOrigin("*")
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config>{

	@Autowired
	private RestTemplate template;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private RouteValidator validator;
	
	@Autowired
	RoleRepository roleRepository;
	
	public AuthenticationFilter() {
		super(Config.class);
	}
	
	@Override
	public GatewayFilter apply(Config config) {
		return ((exchange,chain)->{
			
			String username ;

			if(validator.isSecured.test(exchange.getRequest())) {
				// header contains token or not 
				if(!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
					//throw new RuntimeException("Missing authorization header"); 
					throw new UnauthorisedException(HttpStatusCode.valueOf(402), "Missing authorization header");
				}
				
				String authHeaders = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
				if(authHeaders !=null && authHeaders.startsWith("Bearer ")) {
					System.out.println(authHeaders);
					authHeaders = authHeaders.substring(7);
					System.out.println(authHeaders);
					username = jwtUtil.getUsernameFromToken(authHeaders);
					System.out.println(username);
					List<Role> findAll = roleRepository.findAll();
					System.out.println(findAll);
				}
				try {
					// REST call to SECURITY-SERVICE
					//ResponseEntity<String> forEntity = template.getForEntity("http://SECURITY-SERVICE/security-service/validate?token=" + authHeaders, String.class);
					jwtUtil.validateToken(authHeaders);
				}catch (Exception e) {
					System.out.println("invalid acess");
					//throw new RuntimeException("un authorized access to application");
					throw new UnauthorisedException(HttpStatusCode.valueOf(405), "JWT token already expired" );
				}
			}
			
			return chain.filter(exchange);
		});
	}

	public static class Config{
		
	}
}
