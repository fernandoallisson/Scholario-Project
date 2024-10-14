package com.scholario.scholario_demo.dto.student;

import com.scholario.scholario_demo.entiity.Student;

/**
 * The type Student creation dto.
 */
public record StudentCreationDto(
    String name, String email, String password, String phone, String address,
    String birthdate, Long enrollment, String guardianName,
    String guardianCellPhone) {

  /**
   * To entity student.
   *
   * @return the student
   */
  public Student toEntity() {
    return new Student(
        name, email, password, phone, address, birthdate, enrollment,
        guardianName, guardianCellPhone);
  }
}
