package com.scholario.scholario_demo.dto.user;

import com.scholario.scholario_demo.entiity.User;

public record UserDto(
    Long id, String name, String email, String password, String role, String phone, String address
) {

  public static UserDto fromEntity(User user) {
    return new UserDto(
        user.getId(),
        user.getName(),
        user.getEmail(),
        user.getPassword(),
        user.getRole(),
        user.getPhone(),
        user.getAddress()
    );
  }
}
