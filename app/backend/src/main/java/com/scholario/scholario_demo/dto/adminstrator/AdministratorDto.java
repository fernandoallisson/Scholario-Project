package com.scholario.scholario_demo.dto.adminstrator;

import com.scholario.scholario_demo.entiity.Administrator;

public record AdministratorDto(
    Long id, String name, String email, String password, String phone,
    String address, String birthdate, String department, String hireDate
) {
  public static AdministratorDto fromEntity(Administrator administrator) {
    return new AdministratorDto(
        administrator.getId(),
        administrator.getName(),
        administrator.getEmail(),
        administrator.getPassword(),
        administrator.getPhone(),
        administrator.getAddress(),
        administrator.getBirthdate(),
        administrator.getDepartment(),
        administrator.getHireDate()
    );
  }
}
