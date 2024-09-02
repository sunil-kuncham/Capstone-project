package com.wipro.exception;

import java.nio.file.AccessDeniedException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(UserIdNotFoundException.class)
	public ResponseEntity<ErrorMessage> handleStudentIdNotFoundException(UserIdNotFoundException exception) {

		return new ResponseEntity<ErrorMessage>(new ErrorMessage("USER_ID_NOT_FOUND", exception.getMessage()),
				HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException me) {
		Map<String, String> errorMap = new HashMap<>();

		me.getBindingResult().getAllErrors().forEach(error -> {
			errorMap.put(((FieldError) error).getField(), error.getDefaultMessage());
		});

		return new ResponseEntity<Object>(errorMap, HttpStatus.BAD_REQUEST);

	}
	@ExceptionHandler(DuplicateEmailException.class)
	public ResponseEntity<String> handleDuplicateEmailException(DuplicateEmailException ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(Exception.class)
	public ProblemDetail handleSecurityException(Exception ex) {

		ProblemDetail errorDetail;

		if (ex instanceof BadCredentialsException) {
			errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(401), ex.getMessage());
			errorDetail.setProperty("access_denied_reason", "Authentication Failure (Username or Password incorrect)");
		} else if (ex instanceof AccessDeniedException) {
			errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), ex.getMessage());
			errorDetail.setProperty("access_denied_reason", "Not Authorized!");
		} else if (ex instanceof SignatureException) {
			errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), ex.getMessage());
			errorDetail.setProperty("access_denied_reason", "JWT Signature not valid");
		} else if (ex instanceof ExpiredJwtException) {
			errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), ex.getMessage());
			errorDetail.setProperty("access_denied_reason", "JWT Token already expired!");
		} else {
			// Default case for unexpected exceptions
			errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(500), "An unexpected error occurred");
			errorDetail.setProperty("error", ex.getClass().getSimpleName());
			errorDetail.setProperty("message", ex.getMessage());
		}

		return errorDetail;
	}

}
