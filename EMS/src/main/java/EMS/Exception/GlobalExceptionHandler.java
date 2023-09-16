package EMS.Exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(EmployeeNotFoundException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public ProblemDetail hanleEmployeeNotFoundException(EmployeeNotFoundException ex) {
		ProblemDetail errorDetails = null;
		
		errorDetails = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(404), ex.getMessage());
		errorDetails.setProperty("errorMessage", ex.getMessage());
		
		//Map<String, Object> error = new HashMap<>();
		//error.put("errorMessage", ex.getMessage());
		//error.put("httpStatus", HttpStatus.NOT_FOUND);
		return errorDetails;
	}

}
