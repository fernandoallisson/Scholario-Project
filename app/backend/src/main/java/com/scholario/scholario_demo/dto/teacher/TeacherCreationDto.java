package com.scholario.scholario_demo.dto.teacher;

import java.util.List;

import com.scholario.scholario_demo.entiity.Teacher;

/**
 * The type Teacher creation dto.
 */
public record TeacherCreationDto(
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
    String department,
    String hireDate,
    List<String> specialConditions,
    List<String> allergiesList,
    List<String> disabilities
) {

  /**
   * To entity teacher.
   *
   * @return the teacher
   */
  public Teacher toEntity() {
    return new Teacher(
        name, email, password, phone, address, birthdate, colorRace, disabilities, bloodType, 
        nationality, specialConditions, allergiesList, sex, cpfNumber, image, department, hireDate
    );
  }
}
