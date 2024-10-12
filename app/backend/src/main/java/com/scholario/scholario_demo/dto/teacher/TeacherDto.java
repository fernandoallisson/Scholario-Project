package com.scholario.scholario_demo.dto.teacher;

import com.scholario.scholario_demo.dto.classes.ClassDto;
import com.scholario.scholario_demo.dto.subject.SubjectDto;
import com.scholario.scholario_demo.entiity.Teacher;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public record TeacherDto(
    Long id, String name, String email, String password, String phone, String address,
    String birthdate, String department, String hireDate,
    List<SubjectDto> subjects, List<ClassDto> classes
) {
  public static TeacherDto fromEntity(Teacher teacher) {
    if (teacher == null) {
      throw new IllegalArgumentException("Teacher must not be null");
    }

    List<SubjectDto> subjectDtos = new ArrayList<>();
    if (teacher.getSubject() != null) {
      subjectDtos = teacher.getSubject().stream()
          .filter(Objects::nonNull) // Filtra objetos nulos
          .map(SubjectDto::fromEntity)
          .collect(Collectors.toList()); // Coleta em uma lista
    }

    List<ClassDto> classDtos = new ArrayList<>();
    if (teacher.getClassesTeachers() != null) {
      classDtos = teacher.getClassesTeachers().stream()
          .filter(Objects::nonNull) // Filtra objetos nulos
          .map(ClassDto::fromEntity)
          .collect(Collectors.toList()); // Coleta em uma lista
    }

    return new TeacherDto(
        teacher.getId(),
        teacher.getName(),
        teacher.getEmail(),
        teacher.getPassword(),
        teacher.getPhone(),
        teacher.getAddress(),
        teacher.getBirthdate(),
        teacher.getDepartment(),
        teacher.getHireDate(),
        subjectDtos,
        classDtos
    );
  }

}
