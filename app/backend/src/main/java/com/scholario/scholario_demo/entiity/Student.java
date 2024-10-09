package com.scholario.scholario_demo.entiity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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

  public List<Classe> getClasses() {
    return classesStudents;
  }

  public void setClasses(List<Classe> classes) {
    this.classesStudents = classes;
  }
}
