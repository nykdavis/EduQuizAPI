package com.davis.piersqure.eduquizAPI.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<String> handleInternalServerError(Exception ex) {
		log.error("Internal Server Error : {}", ex.getMessage());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body("Internal server error , Please try again leter.");

	}

	@ExceptionHandler(NotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<String> handleNotFoundException(NotFoundException ex) {
		log.error("Not Found : {}", ex.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());

	}

	
	public ResponseEntity<String> handleBadRequestException(BadRequestException ex){
		log.error("Bad Request : {}",ex.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
	}
}
