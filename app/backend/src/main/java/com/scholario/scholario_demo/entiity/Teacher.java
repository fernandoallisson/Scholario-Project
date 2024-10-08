package com.scholario.scholario_demo.entiity;

import jakarta.persistence.JoinTable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;

@Entity
@DiscriminatorValue("teacher")
public class Teacher extends User {

  private String department;
  private String hireDate;

  @ManyToMany
  @JoinTable(
      name = "teachers_subjects",
      joinColumns = @JoinColumn(name = "teacher_id"),
      inverseJoinColumns = @JoinColumn(name = "subject_id")
  )
 private List<Subject> subject = new ArrayList<>();

  public Teacher() {
  }

  public Teacher(String name, String email, String password, String phone,
      String address, String birthdate, String department, String hireDate) {
    super(name, email, password, phone, address, birthdate);
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

  public List<Subject> getSubject() {
    return subject;
  }

  public void setSubject(List<Subject> subject) {
    this.subject = subject;
  }
}
