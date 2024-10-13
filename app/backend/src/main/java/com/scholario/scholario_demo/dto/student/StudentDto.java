package com.scholario.scholario_demo.dto.student;

import com.scholario.scholario_demo.dto.classes.ClassDto;
import com.scholario.scholario_demo.entiity.Student;
import java.util.List;

public record StudentDto(Long id, String name, String email, String phone, String address,
   String birthdate, Long enrollment, String guardianName,
   String guardianCellPhone, List<ClassDto> classes) {

  public static StudentDto fromEntity(Student student) {
    return new StudentDto(
        student.getId(),
        student.getName(),
        student.getEmail(),
        student.getPhone(),
        student.getAddress(),
        student.getBirthdate(),
        student.getEnrollment(),
        student.getGuardianName(),
        student.getGuardianCellPhone(),
        student.getClassesStudents().stream()
            .map(ClassDto::fromEntity)
            .toList()
    );
  }
}
