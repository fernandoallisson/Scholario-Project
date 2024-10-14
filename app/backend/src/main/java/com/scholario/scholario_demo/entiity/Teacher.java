package com.scholario.scholario_demo.entiity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * The type Teacher.
 */
@Setter
@Getter
@Entity
@DiscriminatorValue("teacher")
public class Teacher extends User {

  private String department;
  private String hireDate;

  @ManyToMany
  @JoinTable(
      name = "teachers_subjects",
      joinColumns = @JoinColumn(name = "teacher_id"),
      inverseJoinColumns = @JoinColumn(name = "subject_id")
  )
 private List<Subject> subject = new ArrayList<>();

  @ManyToMany
  @JoinTable(
      name = "teachers_classes",
      joinColumns = @JoinColumn(name = "teacher_id"),
      inverseJoinColumns = @JoinColumn(name = "class_id")
  )
  private List<Classe> classesTeachers = new ArrayList<>();

  /**
   * Instantiates a new Teacher.
   */
  public Teacher() {
  }

  /**
   * Instantiates a new Teacher.
   *
   * @param name       the name
   * @param email      the email
   * @param password   the password
   * @param phone      the phone
   * @param address    the address
   * @param birthdate  the birthdate
   * @param department the department
   * @param hireDate   the hire date
   */
  public Teacher(String name, String email, String password, String phone,
      String address, String birthdate, String department, String hireDate) {
    super(name, email, password, phone, address, birthdate);
    this.department = department;
    this.hireDate = hireDate;
  }

}
