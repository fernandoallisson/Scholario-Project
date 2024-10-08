package com.scholario.scholario_demo.dto.adminstrator;

import com.scholario.scholario_demo.entiity.Administrator;

public record AdministratorCreationDto(
    String name, String email, String password, String phone,
    String address, String birthdate, String department, String hireDate
) {
  public Administrator toEntity() {
    return new Administrator(
        name, email, password, phone, address, birthdate, department, hireDate
    );
  }
}
