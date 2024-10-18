package com.scholario.scholario_demo.entiity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * The type Grade.
 */
@Setter
@Getter
@Entity
@Table(name = "grades")
public class Grade {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "grade_seq")
  @SequenceGenerator(name = "grade_seq", sequenceName = "grade_sequence", initialValue = 4234568, allocationSize = 1)
  private Long id;

  private String date;

  @ManyToOne
  @JoinColumn(name = "student_id", nullable = false)
  private Student studentGrades;

  @ManyToOne
  @JoinColumn(name = "subject_id", nullable = false)
  private Subject subjectGrades;

  private double firstGrade = 0.0;
  private double secondGrade = 0.0;
  private double thirdGrade = 0.0;
  private double fourthGrade = 0.0;
  private int year;

  /**
   * Instantiates a new Grade.
   */
  public Grade() {
  }

  /**
   * Instantiates a new Grade.
   *
   * @param date       the date
   * @param firstGrade the first grade
   * @param secondGrade the second grade
   * @param thirdGrade the third grade
   * @param year the year
   * 
   */
  public Grade( String date, double firstGrade, 
  double secondGrade, double thirdGrade, double fourthGrade, int year) {
    this.date = date;
    this.firstGrade = firstGrade;
    this.secondGrade = secondGrade;
    this.thirdGrade = thirdGrade;
    this.fourthGrade = fourthGrade;
    this.year = year;
  }

}
