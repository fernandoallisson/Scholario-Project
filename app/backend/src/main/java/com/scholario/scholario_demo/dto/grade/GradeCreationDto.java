package com.scholario.scholario_demo.dto.grade;

import com.scholario.scholario_demo.entiity.Grade;

/**
 * The type Grade creation dto.
 */
public record GradeCreationDto(
    double firstGrade,
    double secondGrade,
    double thirdGrade,
    double fourthGrade,
    int year,
    String date
) {

  /**
   * To entity grade.
   *
   * @return the grade
   */
  public Grade toEntity() {
    return new Grade(
        this.date(),
        this.firstGrade(),
        this.secondGrade(),
        this.thirdGrade(),
        this.fourthGrade(),
        this.year()
    );
  }
}
