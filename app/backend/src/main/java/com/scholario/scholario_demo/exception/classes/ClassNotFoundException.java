package com.scholario.scholario_demo.exception.classes;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The type Class not found exception.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ClassNotFoundException extends RuntimeException {

  /**
   * Instantiates a new Class not found exception.
   *
   * @param message the message
   */
  public ClassNotFoundException(String message) {
    super(message);
  }
}
