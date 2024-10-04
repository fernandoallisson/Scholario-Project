package com.scholario.scholario_demo.entiity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@DiscriminatorValue("teacher")
public class Teacher extends User{

  private String department;
  private String hireDate;

 @ManyToOne
 @JoinColumn(name = "subject_id")
 private Subject subject;

  public Teacher() {
  }

  public Teacher(String name, String email, String password, String role, String phone,
      String address, String birthdate, String department, String hireDate) {
    super(name, email, password, role, phone, address, birthdate);
    this.department = department;
    this.hireDate = hireDate;
  }

  public String getDepartment() {
    return department;
  }

  public void setDepartment(String department) {
    this.department = department;
  }

  public String getHireDate() {
    return hireDate;
  }

  public void setHireDate(String hireDate) {
    this.hireDate = hireDate;
  }

 public Subject getSubject() {
   return subject;
 }

 public void setSubject(Subject subject) {
   this.subject = subject;
 }
}
