package com.geekyants.exception;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.geekyants.service.LogService;

@ControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@Autowired
	private LogService logService;

	// handling specific exception
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> resourceNotFoundHandling(ResourceNotFoundException exception, WebRequest request) {
		ErrorModel errorModel = new ErrorModel(new Date(), exception.getMessage(), request.getDescription(false));

		logger.error(logService.formatLogResponse(request.getDescription(false), exception.getMessage()).toString());
		return new ResponseEntity<>(errorModel, HttpStatus.NOT_FOUND);
	}

	// handling global exception
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> globalExceptionHandling(Exception exception, WebRequest request) {
		ErrorModel errorModel = new ErrorModel(new Date(), exception.getMessage(), request.getDescription(false));
		logger.error(logService.formatLogResponse(request.getDescription(false), exception.getMessage()).toString());
		return new ResponseEntity<>(errorModel, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}