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
 * The type Attendance.
 */
@Setter
@Getter
@Entity
@Table(name = "attendances")
public class Attendance {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "attendance_seq")
  @SequenceGenerator(name = "attendance_seq", sequenceName = "attendance_sequence", initialValue = 5234568, allocationSize = 1)
  private Long id;

  private String date;
  private String status;

  @ManyToOne
  @JoinColumn(name = "student_id")
  private Student studentAttendances;

  @ManyToOne
  @JoinColumn(name = "class_id")
  private Classe classeAttendances;

  /**
   * Instantiates a new Attendance.
   */
  public Attendance() {
  }

  /**
   * Instantiates a new Attendance.
   *
   * @param date   the date
   * @param status the status
   */
  public Attendance(String date, String status) {
    this.date = date;
    this.status = status;
  }

}
