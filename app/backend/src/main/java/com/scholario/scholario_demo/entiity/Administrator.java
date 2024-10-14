package com.scholario.scholario_demo.entiity;

import java.util.List;

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
   * @param name              the name
   * @param email             the email
   * @param password          the password
   * @param phone             the phone
   * @param address           the address
   * @param birthdate         the birthdate
   * @param colorRace         the color race
   * @param deficiency        the deficiency
   * @param bloodType         the blood type
   * @param nationality       the nationality
   * @param specialConditions the special conditions
   * @param allergiesList     the allergies list
   * @param sex               the sex
   * @param cpfNumber         the cpf number
   * @param image             the image
   * @param department        the department
   * @param hireDate          the hire date
   */
  public Administrator(String name, String email, String password, String phone, String address,
      String birthdate, String colorRace, List<String> disabilities, String bloodType,
      String nationality, List<String> specialConditions,
      List<String> allergiesList, String sex, String cpfNumber, String image,
      String department, String hireDate) {
    super(name, email, password, phone, address, birthdate, colorRace, disabilities, bloodType,
        nationality, specialConditions, allergiesList, sex, cpfNumber, image);
    this.department = department;
    this.hireDate = hireDate;
  }

}
