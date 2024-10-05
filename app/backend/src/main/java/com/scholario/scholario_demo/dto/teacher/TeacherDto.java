package com.scholario.scholario_demo.dto.teacher;

import com.scholario.scholario_demo.dto.subject.SubjectDto;
import com.scholario.scholario_demo.entiity.Teacher;
import java.util.List;

public record TeacherDto(
    Long id, String name, String email, String password,
    String role, String phone, String address,
    String birthdate, String department, String hireDate,
    List<SubjectDto> subjects
    ) {

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
        teacher.getHireDate(),
        teacher.getSubject().stream()
            .map(SubjectDto::fromEntity)
            .toList()
    );
  }
}
