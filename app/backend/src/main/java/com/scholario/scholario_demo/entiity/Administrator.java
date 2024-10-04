package com.scholario.scholario_demo.entiity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("admin")
public class Administrator extends User {

  private String department;
  private String hireDate;

  public Administrator(){}

  public Administrator(String name, String email, String password, String role, String phone,
      String address,
      String birthdate, String department, String hireDate) {
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
}
