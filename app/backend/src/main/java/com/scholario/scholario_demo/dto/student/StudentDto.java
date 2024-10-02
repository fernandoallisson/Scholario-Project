package com.scholario.scholario_demo.dto.student;

import com.scholario.scholario_demo.entiity.Student;

public record StudentDto(Long id, Long enrollmentNumber, String birthdate, String guardianName, String guardianCellPhone) {

  public static StudentDto fromEntity(Student student) {
    return new StudentDto(
        student.getId(),
        student.getEnrollment_number(),
        student.getBirthdate(),
        student.getGuardianName(),
        student.getGuardianCellPhone()
    );
  }
}
