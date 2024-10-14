package com.scholario.scholario_demo.exception.subject;

/**
 * The type Subject not found exception.
 */
public class SubjectNotFoundException extends RuntimeException {

  /**
   * Instantiates a new Subject not found exception.
   *
   * @param message the message
   */
  public SubjectNotFoundException(String message) {
    super(message);
  }
}
