package autoServer.config;

import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.id.IdentifierGenerationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;

import autoServer.services.impl.TestSuiteServices;

@ControllerAdvice
public class handleValideException {
	private static final Logger LOGGER = LogManager.getLogger(TestSuiteServices.class);
	// handle exception validate 
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String> > handleValidationExceptions(
			MethodArgumentNotValidException ex) {
	    Map<String, String> errors = new HashMap<>();
	    ex.getBindingResult().getAllErrors().forEach((error) -> {
	        String fieldName = ((FieldError) error).getField();
	        String errorMessage = error.getDefaultMessage();
	        errors.put(fieldName, errorMessage);
	        LOGGER.error(errorMessage);
	    });
	    return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
	}
	
	//handle SQL EXCEPTION
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(IdentifierGenerationException.class)
	public ResponseEntity<String> handleIdentifierGenerationException(IdentifierGenerationException e){
		 LOGGER.error(e.getMessage());
		 return new ResponseEntity<>("ID is out of memory",HttpStatus.BAD_REQUEST);
		
	}
	
	//handle check UUID
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<String> handleUUIDException(HttpMessageNotReadableException e){
		 LOGGER.error(e.getMessage());
		 return new ResponseEntity<>("UUID is not format",HttpStatus.BAD_REQUEST);
		
	}
	
	//handle validate cloumn in database
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	public ResponseEntity<String> handleColumnException(SQLIntegrityConstraintViolationException e){
		 LOGGER.error(e.getMessage());
		 return new ResponseEntity<>("Server error pls wait",HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	//Handle null value
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<String> handleNullPointException(NullPointerException e){
		 LOGGER.error(e.getMessage());
		 return new ResponseEntity<>("",HttpStatus.BAD_REQUEST);
	}
	
}
