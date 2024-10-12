package com.scholario.scholario_demo.dto.grade;

import com.scholario.scholario_demo.entiity.Grade;

public record GradeCreationDto(
  double gradeValue,
  String date
) {
  public Grade toEntity() {
    return new Grade(
      gradeValue, date
    );
  }
}
