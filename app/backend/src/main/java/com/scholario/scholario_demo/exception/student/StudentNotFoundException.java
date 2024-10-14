package com.scholario.scholario_demo.exception.student;

/**
 * The type Student not found exception.
 */
public class StudentNotFoundException extends RuntimeException {

  /**
   * Instantiates a new Student not found exception.
   *
   * @param message the message
   */
  public StudentNotFoundException(String message) {
    super(message);
  }

}
