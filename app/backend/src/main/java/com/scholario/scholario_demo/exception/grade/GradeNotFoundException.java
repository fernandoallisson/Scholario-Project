package com.scholario.scholario_demo.exception.grade;

/**
 * The type Grade not found exception.
 */
public class GradeNotFoundException extends RuntimeException {

  /**
   * Instantiates a new Grade not found exception.
   *
   * @param message the message
   */
  public GradeNotFoundException(String message) {
    super(message);
  }
}
