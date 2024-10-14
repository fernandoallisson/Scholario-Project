package com.scholario.scholario_demo.dto.student;

import java.util.List;

import com.scholario.scholario_demo.entiity.Student;

/**
 * The type Student creation dto.
 */
public record StudentCreationDto(
    String name,
    String email,
    String password,
    String phone,
    String address,
    String birthdate,
    String colorRace,
    String bloodType,
    String nationality,
    String sex,
    String cpfNumber,
    String image,
    List<String> specialConditions,
    List<String> allergiesList,
    List<String> disabilities
    ) {

  /**
   * To entity student.
   *
   * @return the student
   */
  public Student toEntity() {
    return new Student(
        name,
        email,
        password,
        phone,
        address,
        birthdate,
        colorRace,
        disabilities,
        bloodType,
        nationality,
        specialConditions,
        allergiesList,
        sex,
        cpfNumber,
        image
    );
  }
}
