package com.gateway.APIGatewayService;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fallback")
public class FallbackController {

	@GetMapping("/employeeApp")
	public ResponseEntity<String> employeeAppRallback(){
		return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body("EmployeeApp is down");
	}
	
	@GetMapping("/securityApp")
	public ResponseEntity<String> securityAppRallback(){
		return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body("SecurityApp is down");
	}
}
