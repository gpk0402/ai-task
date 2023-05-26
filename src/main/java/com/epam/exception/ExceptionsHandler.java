package com.epam.exception;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class ExceptionsHandler {
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ExceptionObject handleMethodArgumentNotValidException(MethodArgumentNotValidException e, WebRequest w) {
		log.error("MethodArgumentNotValidException: {}", ExceptionUtils.getStackTrace(e));
		List<String> s = new ArrayList<>();
		e.getAllErrors().forEach(e1 -> s.add(e1.getDefaultMessage()));
		return new ExceptionObject(new Date().toString(), HttpStatus.BAD_REQUEST.toString(), s.toString(),
				w.getDescription(false));
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ExceptionObject handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e,
			WebRequest w) {
		log.error("MethodArgumentTypeMismatchException: {}",ExceptionUtils.getStackTrace(e));
		return new ExceptionObject(new Date().toString(), HttpStatus.BAD_REQUEST.toString(), e.getMessage(),
				w.getDescription(false));
	}

	@ExceptionHandler(AssociateException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ExceptionObject handleBatchException(AssociateException e, WebRequest w) {
		log.error("Associate Exception: {}",ExceptionUtils.getStackTrace(e));
		return new ExceptionObject(new Date().toString(), HttpStatus.NOT_FOUND.toString(), e.getMessage(),
				w.getDescription(false));
	}

	@ExceptionHandler(SQLException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ExceptionObject handleSQLException(SQLException e, WebRequest w) {
		log.error("Encountered SQLException: {}",ExceptionUtils.getStackTrace(e));
		return new ExceptionObject(new Date().toString(), HttpStatus.INTERNAL_SERVER_ERROR.toString(), e.getMessage(),
				w.getDescription(false));
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ExceptionObject handleDataIntegrityViolationException(DataIntegrityViolationException e, WebRequest w) {
		log.error("Encountered DataIntegrityViolationException:{}", ExceptionUtils.getStackTrace(e));
		return new ExceptionObject(new Date().toString(), HttpStatus.INTERNAL_SERVER_ERROR.toString(), e.getMessage(),
				w.getDescription(false));
	}

}
