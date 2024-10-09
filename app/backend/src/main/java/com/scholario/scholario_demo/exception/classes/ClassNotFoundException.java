package com.scholario.scholario_demo.exception.classes;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ClassNotFoundException extends RuntimeException {

  public ClassNotFoundException(String message) {
    super(message);
  }
}
