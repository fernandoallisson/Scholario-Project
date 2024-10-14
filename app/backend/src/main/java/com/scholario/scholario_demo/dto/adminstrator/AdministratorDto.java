package com.scholario.scholario_demo.dto.adminstrator;

import java.util.List;

import com.scholario.scholario_demo.entiity.Administrator;

/**
 * The type Administrator dto.
 */
public record AdministratorDto(
    Long id,
    String name,
    String email,
    String phone,
    String address,
    String birthdate,
    String colorRace,
    String bloodType,
    String nationality,
    String sex,
    String cpfNumber,
    String department,
    String hireDate,
    String image,
    List<String> specialConditions,
    List<String> allergiesList,
    List<String> disabilities) {

  /**
   * From entity administrator dto.
   *
   * @param administrator the administrator
   * @return the administrator dto
   */
  public static AdministratorDto fromEntity(Administrator administrator) {
    return new AdministratorDto(
        administrator.getId(),
        administrator.getName(),
        administrator.getEmail(),
        administrator.getPhone(),
        administrator.getAddress(),
        administrator.getBirthdate(),
        administrator.getColorRace(),
        administrator.getBloodType(),
        administrator.getNationality(),
        administrator.getSex(),
        administrator.getCpfNumber(),
        administrator.getDepartment(),
        administrator.getHireDate(),
        administrator.getImage(),
        administrator.getSpecialConditions(),
        administrator.getAllergiesList(),
        administrator.getDisabilities());
  }
}
