package com.scholario.scholario_demo.entiity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;


/**
 * The type Student.
 */
@Setter
@Getter
@Entity
@DiscriminatorValue("student")
public class Student extends User {

  @ManyToMany
  @JoinTable(
      name = "students_classes",
      joinColumns = @JoinColumn(name = "student_id"),
      inverseJoinColumns = @JoinColumn(name = "class_id")
  )
  private List<Classe> classesStudents = new ArrayList<>();

  @OneToMany(mappedBy = "studentAttendances", cascade = CascadeType.ALL)
  private List<Attendance> attendances = new ArrayList<>();

  @OneToMany(mappedBy = "studentGrades", cascade = CascadeType.ALL)
  private List<Grade> grades = new ArrayList<>();

  @ManyToOne
  @JoinColumn(name = "parent_id")
  private Parent parent;

  /**
   * Instantiates a new Student.
   */
  public Student() {}

    /**
   * Instantiates a new Student.
   *
   * @param name              the name
   * @param email             the email
   * @param password          the password
   * @param phone             the phone
   * @param address           the address
   * @param birthdate         the birthdate
   * @param colorRace         the color race
   * @param deficiency        the deficiency
   * @param bloodType         the blood type
   * @param nationality       the nationality
   * @param specialConditions the special conditions
   * @param allergiesList     the allergies list
   * @param sex               the sex
   * @param cpfNumber         the cpf number
   * @param image             the image
   * @param classesStudents   the classes students
   * @param attendances       the attendances
   * @param grades            the grades
   * @param parent            the parent
   */
  public Student(String name, String email, String password, String phone, String address,
      String birthdate, String colorRace, List<String> disabilities, String bloodType,
      String nationality, List<String> specialConditions, List<String> allergiesList, String sex,
      String cpfNumber, String image) {
    super(name, email, password, phone, address, birthdate, colorRace, disabilities, bloodType,
        nationality, specialConditions, allergiesList, sex, cpfNumber, image);
  }

}
