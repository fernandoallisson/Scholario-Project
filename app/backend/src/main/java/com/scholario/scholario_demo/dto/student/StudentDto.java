package com.scholario.scholario_demo.dto.student;

import com.scholario.scholario_demo.entiity.Student;

public record StudentDto(Long id, String name, String email,
String password, String role, String phone, String address,
String birthdate, Long enrollment, String guardianName,
String guardianCellPhone) {

  public static StudentDto fromEntity(Student student) {
    return new StudentDto(
        student.getId(),
        student.getName(),
        student.getEmail(),
        student.getPassword(),
        student.getRole(),
        student.getPhone(),
        student.getAddress(),
        student.getBirthdate(),
        student.getEnrollment(),
        student.getGuardianName(),
        student.getGuardianCellPhone()
    );
  }
}
