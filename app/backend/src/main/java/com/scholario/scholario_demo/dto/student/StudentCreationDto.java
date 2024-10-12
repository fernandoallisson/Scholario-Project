package com.scholario.scholario_demo.dto.student;

import com.scholario.scholario_demo.entiity.Student;

public record StudentCreationDto(
    String name, String email, String password, String phone, String address,
    String birthdate, Long enrollment, String guardianName,
    String guardianCellPhone) {

  public Student toEntity() {
    return new Student(
        name, email, password, phone, address, birthdate, enrollment,
        guardianName, guardianCellPhone);
  }
}
