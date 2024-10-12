package com.scholario.scholario_demo.dto.grade;

import com.scholario.scholario_demo.entiity.Grade;

public record GradeDto(
    Long id,
    Long studentId,
    String name,
    Long subjectId,
    String subjectName,
    double gradeValue,
    String date) {

  public static GradeDto fromEntity(Grade grade) {
    return new GradeDto(
        grade.getId(),
        grade.getStudentGrades().getId(),
        grade.getStudentGrades().getName(),
        grade.getSubjectGrades().getId(),
        grade.getSubjectGrades().getName(),
        grade.getGradeValue(),
        grade.getDate());
  }

}
