package com.scholario.scholario_demo.exception.attendance;

/**
 * The type Attendance not found exception.
 */
public class AttendanceNotFoundException extends RuntimeException {

  /**
   * Instantiates a new Attendance not found exception.
   *
   * @param message the message
   */
  public AttendanceNotFoundException(String message) {
    super(message);
  }
}
