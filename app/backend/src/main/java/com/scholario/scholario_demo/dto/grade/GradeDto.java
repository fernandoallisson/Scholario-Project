package com.scholario.scholario_demo.dto.grade;

import com.scholario.scholario_demo.entiity.Grade;

/**
 * The type Grade dto.
 */
public record GradeDto(
    Long id,
    Long studentId,
    String name,
    Long subjectId,
    String subjectName,
    double firstGrade,
    double secondGrade,
    double thirdGrade,
    double fourthGrade,
    int year,
    String date) {

  /**
   * From entity grade dto.
   *
   * @param grade the grade
   * @return the grade dto
   */
  public static GradeDto fromEntity(Grade grade) {
    return new GradeDto(
        grade.getId(),
        grade.getStudentGrades().getId(),
        grade.getStudentGrades().getName(),
        grade.getSubjectGrades().getId(),
        grade.getSubjectGrades().getName(),
        grade.getFirstGrade(),
        grade.getSecondGrade(),
        grade.getThirdGrade(),
        grade.getFourthGrade(),
        grade.getYear(),
        grade.getDate());
  }

}
