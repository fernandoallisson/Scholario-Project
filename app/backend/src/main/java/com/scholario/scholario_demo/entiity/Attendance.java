package com.scholario.scholario_demo.entiity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "attendances")
public class Attendance {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String date;
  private String status;

  @ManyToOne
  @JoinColumn(name = "student_id")
  private Student studentAttendances;

  @ManyToOne
  @JoinColumn(name = "class_id")
  private Classe classeAttendances;

  public Attendance() {
  }

  public Attendance(String date, String status) {
    this.date = date;
    this.status = status;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public Student getStudentAttendances() {
    return studentAttendances;
  }

  public void setStudentAttendances(Student studentAttendances) {
    this.studentAttendances = studentAttendances;
  }

  public Classe getClasseAttendances() {
    return classeAttendances;
  }

  public void setClasseAttendances(Classe classeAttendances) {
    this.classeAttendances = classeAttendances;
  }
}
