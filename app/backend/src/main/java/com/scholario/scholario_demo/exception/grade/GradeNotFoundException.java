package com.scholario.scholario_demo.exception.grade;

public class GradeNotFoundException extends RuntimeException {

  public GradeNotFoundException(String message) {
    super(message);
  }
}
