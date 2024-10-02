package com.scholario.scholario_demo.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.scholario.scholario_demo.exception.UserException;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(UserException.class)
  public ResponseEntity<String> handleUserException(UserException ex) {
    if (ex.getMessage().contains("not found")) {
      return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    } else if (ex.getMessage().contains("Unauthorized")) {
      return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
    } else if (ex.getMessage().contains("already exists")) {
      return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    } else if (ex.getMessage().contains("Bad request")) {
      return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    } else {
      return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
