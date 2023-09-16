package com.security.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(EmployeeNotFoundException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public Map<String, Object> hanleEmployeeNotFoundException(EmployeeNotFoundException ex) {
		Map<String, Object> error = new HashMap<>();
		error.put("errorMessage", ex.getMessage());
		error.put("httpStatus", HttpStatus.NOT_FOUND);
		return error;
	}

	// Security exception
	@ExceptionHandler(Exception.class)
	public ProblemDetail handleSecurityException(Exception ex) {
		ProblemDetail errorDetails = null;

		if (ex instanceof BadCredentialsException) {
			errorDetails = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(401), ex.getMessage());
			errorDetails.setProperty("access_denied_reason", "Authentication Failure");
		}

		if (ex instanceof AccessDeniedException) {
			errorDetails = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), ex.getMessage());
			errorDetails.setProperty("access_denied_reason", "Not_authorized");
		}

		if (ex instanceof SignatureException) {
			errorDetails = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), ex.getMessage());
			errorDetails.setProperty("access_denied_reason", "JWT signature not valid");
		}
		
		if (ex instanceof ExpiredJwtException) {
			errorDetails = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(405), ex.getMessage());
			errorDetails.setProperty("access_denied_reason", "JWT token already expired !");
		}

		return errorDetails;
	}
}
