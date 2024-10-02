package com.scholario.scholario_demo.dto.user;

import com.scholario.scholario_demo.entiity.User;

public record UserCreationDto(
    String name, String email, String password, String role, String phone, String address, String birthdate
) {

  public User toEntity() {
    return new User(name, email, password, role, phone, address, birthdate);
  }
}
