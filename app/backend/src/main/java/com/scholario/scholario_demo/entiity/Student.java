package com.scholario.scholario_demo.entiity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
public class Student extends User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long enrollment;
  private String guardianName;
  private String guardianCellPhone;

  public Student(){}

  public Student(String name, String email, String password, String role, String phone,
      String address, String birthdate, Long enrollment, String guardianName,
      String guardianCellPhone) {
    super(name, email, password, role, phone, address, birthdate);
    this.enrollment = enrollment;
    this.guardianName = guardianName;
    this.guardianCellPhone = guardianCellPhone;
  }

   public Long getEnrollment() {
    return enrollment;
  }

  public void setEnrollment(Long enrollment) {
    this.enrollment = enrollment;
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
}
