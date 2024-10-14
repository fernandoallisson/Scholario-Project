package com.scholario.scholario_demo.dto.adminstrator;

import java.util.List;

import com.scholario.scholario_demo.entiity.Administrator;

/**
 * The type Administrator creation dto.
 */
public record AdministratorCreationDto(
    String name,
    String email,
    String password,
    String phone,
    String address,
    String birthdate,
    String colorRace,
    List<String> disabilities,
    String bloodType,
    String nationality,
    List<String> specialConditions,
    List<String> allergiesList,
    String sex,
    String cpfNumber,
    String department,
    String hireDate,
    String image
) {

  /**
   * To entity administrator.
   *
   * @return the administrator
   */
  public Administrator toEntity() {
    return new Administrator(
        name,
        email,
        password,
        phone,
        address,
        birthdate,
        colorRace,
        disabilities,
        bloodType,
        nationality,
        specialConditions,
        allergiesList,
        sex,
        cpfNumber,
        image,
        department,
        hireDate
    );
  }
}
