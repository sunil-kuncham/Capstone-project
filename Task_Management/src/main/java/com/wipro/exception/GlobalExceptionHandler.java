package com.wipro.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;



	@ControllerAdvice
	public class GlobalExceptionHandler {
		
		@ExceptionHandler(TaskIdNotFoundException.class)
		public ResponseEntity<ErrorMessage> handleStudentIdNotFoundException(TaskIdNotFoundException exception)
		{
			
			return new ResponseEntity<ErrorMessage>(new ErrorMessage("Task_ID_NOT_FOUND",exception.getMessage()),HttpStatus.NOT_FOUND);
			
		}
		
		@ExceptionHandler(MethodArgumentNotValidException.class)
		public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException me)
		{
			Map<String,String> errorMap = new HashMap<>();
			
			me.getBindingResult().getAllErrors().forEach(
					error -> {
						    errorMap.put(((FieldError)error).getField()    , error.getDefaultMessage());
					});
			
			return new ResponseEntity<Object>(errorMap,HttpStatus.BAD_REQUEST);
			
			
		}
		

	}
	

