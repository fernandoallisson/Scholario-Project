package com.scholario.scholario_demo.entiity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

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

  @OneToMany(mappedBy = "studentAttendances")
  private List<Attendance> attendances = new ArrayList<>();

  public Student(){}

  public Student(String name, String email, String password, String phone,
      String address, String birthdate, Long enrollment, String guardianName,
      String guardianCellPhone) {
    super(name, email, password, phone, address, birthdate);
    this.enrollment = enrollment;
    this.guardianName = guardianName;
    this.guardianCellPhone = guardianCellPhone;
  }

   public Long getEnrollment() {
    return enrollment;
  }

  public void setEnrollment(Long enrollment) {
    this.enrollment = enrollment;
  }

  public String getGuardianName() {
    return guardianName;
  }

  public void setGuardianName(String guardianName) {
    this.guardianName = guardianName;
  }

  public String getGuardianCellPhone() {
    return guardianCellPhone;
  }

  public void setGuardianCellPhone(String guardianCellPhone) {
    this.guardianCellPhone = guardianCellPhone;
  }

  public List<Attendance> getAttendances() {
    return attendances;
  }

  public void setAttendances(List<Attendance> attendances) {
    this.attendances = attendances;
  }

  public List<Classe> getClassesStudents() {
    return classesStudents;
  }

  public void setClassesStudents(List<Classe> classesStudents) {
    this.classesStudents = classesStudents;
  }
}
