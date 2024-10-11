package com.scholario.scholario_demo.exception.attendance;

public class AttendanceNotFoundException extends RuntimeException {

  public AttendanceNotFoundException(String message) {
    super(message);
  }
}
