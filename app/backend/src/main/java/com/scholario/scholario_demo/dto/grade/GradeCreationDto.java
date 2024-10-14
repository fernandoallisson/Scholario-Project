package com.scholario.scholario_demo.dto.grade;

import com.scholario.scholario_demo.entiity.Grade;

/**
 * The type Grade creation dto.
 */
public record GradeCreationDto(
    double gradeValue,
    String date
) {

  /**
   * To entity grade.
   *
   * @return the grade
   */
  public Grade toEntity() {
    return new Grade(
      gradeValue, date
    );
  }
}
