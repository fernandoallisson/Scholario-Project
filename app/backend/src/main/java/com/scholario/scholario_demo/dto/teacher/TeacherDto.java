package com.scholario.scholario_demo.dto.teacher;

import com.scholario.scholario_demo.dto.student.StudentDto;
import com.scholario.scholario_demo.entiity.Teacher;

public record TeacherDto(
    Long id, String name, String email, String password,
    String role, String phone, String address,
    String birthdate, String department, String hireDate) {

  public static TeacherDto fromEntity(Teacher teacher) {
    return new TeacherDto(
        teacher.getId(),
        teacher.getName(),
        teacher.getEmail(),
        teacher.getPassword(),
        teacher.getRole(),
        teacher.getPhone(),
        teacher.getAddress(),
        teacher.getBirthdate(),
        teacher.getDepartment(),
        teacher.getHireDate()
    );
  }
}
