package com.scholario.scholario_demo.entiity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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

  private Long enrollment;
  private String guardianName;
  private String guardianCellPhone;

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
   * @param enrollment        the enrollment
   * @param guardianName      the guardian name
   * @param guardianCellPhone the guardian cell phone
   */
  public Student(String name, String email, String password, String phone,
      String address, String birthdate, Long enrollment, String guardianName,
      String guardianCellPhone) {
    super(name, email, password, phone, address, birthdate);
    this.enrollment = enrollment;
    this.guardianName = guardianName;
    this.guardianCellPhone = guardianCellPhone;
  }

}
