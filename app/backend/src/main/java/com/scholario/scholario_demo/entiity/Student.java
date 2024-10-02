package com.scholario.scholario_demo.entiity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
public class Student {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Long enrollmentNumber;
  private String birthdate;
  private String guardianName;
  private String guardianCellPhone;

  @OneToOne(optional = false)
  @JoinColumn(name = "user_id")
  private User user;

  public Student() {
  }

  public Student(Long enrollment_number, String birthdate, String guardianName,
      String guardianCellPhone) {
    this.enrollmentNumber = enrollment_number;
    this.birthdate = birthdate;
    this.guardianName = guardianName;
    this.guardianCellPhone = guardianCellPhone;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getEnrollment_number() {
    return enrollmentNumber;
  }

  public void setEnrollment_number(Long enrollment_number) {
    this.enrollmentNumber = enrollment_number;
  }

  public String getBirthdate() {
    return birthdate;
  }

  public void setBirthdate(String birthdate) {
    this.birthdate = birthdate;
  }

  public String getGuardianName() {
    return guardianName;
  }

  public void setGuardianName(String guardianName) {
    this.guardianName = guardianName;
  }

  public String getGuardianCellPhone() {
    return guardianCellPhone;
  }

  public void setGuardianCellPhone(String guardianCellPhone) {
    this.guardianCellPhone = guardianCellPhone;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }
}
