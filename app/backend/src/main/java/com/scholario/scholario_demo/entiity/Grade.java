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

  private double gradeValue;
  private String date;

  @ManyToOne
  @JoinColumn(name = "student_id")
  private Student studentGrades;

  @ManyToOne
  @JoinColumn(name = "subject_id")
  private Subject subjectGrades;

  /**
   * Instantiates a new Grade.
   */
  public Grade() {
  }

  /**
   * Instantiates a new Grade.
   *
   * @param gradeValue the grade value
   * @param date       the date
   */
  public Grade(double gradeValue, String date) {
    this.gradeValue = gradeValue;
    this.date = date;
  }

}
