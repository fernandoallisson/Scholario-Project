package com.scholario.scholario_demo.entiity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

/**
 * The type Administrator.
 */
@Setter
@Getter
@Entity
@DiscriminatorValue("admin")
public class Administrator extends User {

  private String department;
  private String hireDate;

  /**
   * Instantiates a new Administrator.
   */
  public Administrator() {}

  /**
   * Instantiates a new Administrator.
   *
   * @param name       the name
   * @param email      the email
   * @param password   the password
   * @param phone      the phone
   * @param address    the address
   * @param birthdate  the birthdate
   * @param department the department
   * @param hireDate   the hire date
   */
  public Administrator(String name, String email, String password, String phone,
      String address,
      String birthdate, String department, String hireDate) {
    super(name, email, password, phone, address, birthdate);
    this.department = department;
    this.hireDate = hireDate;
  }

}
