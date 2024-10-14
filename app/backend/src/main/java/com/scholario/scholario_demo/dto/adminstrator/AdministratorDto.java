package com.scholario.scholario_demo.dto.adminstrator;

import com.scholario.scholario_demo.entiity.Administrator;

/**
 * The type Administrator dto.
 */
public record AdministratorDto(
    Long id, String name, String email, String phone,
    String address, String birthdate, String department, String hireDate
) {

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
        administrator.getDepartment(),
        administrator.getHireDate()
    );
  }
}
