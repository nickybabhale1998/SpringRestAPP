package com.server.exceptions;

import org.hibernate.exception.JDBCConnectionException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice(annotations= {RestController.class,Controller.class})   // handle exception globaly
public class ExceptionManager {
	
	 @ExceptionHandler(value = { RecordNotFoundException.class })      //use handle custom messgae 
	 public ResponseEntity<String> handleRecordNotFoundException(RecordNotFoundException ex){
		 return new ResponseEntity<String>(ex.getMessage(),HttpStatus.NOT_FOUND);
	 }
	 
	 @ExceptionHandler(value= MethodArgumentNotValidException.class)
	 public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
		 return new ResponseEntity<Object>(ex.getMessage(),HttpStatus.NOT_ACCEPTABLE);
	 }
	
	 @ExceptionHandler(value = DatabaseErrorException.class)
	 public ResponseEntity<String> handleDatabaseException(DataAccessException ex){
		 return new ResponseEntity<String>(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
	 }
	 
	 @ExceptionHandler(value=JDBCConnectionException.class)
	 public ResponseEntity<String>handlerJDBCConnectionException(JDBCConnectionException e)
	 {
		 return new ResponseEntity<String>("Databse is not connected "+e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
	 }
}
