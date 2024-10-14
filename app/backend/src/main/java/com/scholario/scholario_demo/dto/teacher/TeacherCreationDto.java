package com.scholario.scholario_demo.dto.teacher;

import com.scholario.scholario_demo.entiity.Teacher;

/**
 * The type Teacher creation dto.
 */
public record TeacherCreationDto(
    String name, String email, String password, String phone, String address,
    String birthdate, Long enrollment, String department, String hireDate) {

  /**
   * To entity teacher.
   *
   * @return the teacher
   */
  public Teacher toEntity() {
    return new Teacher(
      name, email, password, phone, address, birthdate, department, hireDate
    );
  }
}
