package com.scholario.scholario_demo.entiity;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "classes")
public class Classe {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;
  private int year;

  @ManyToMany(mappedBy = "classesTeachers")
  List<Teacher> teachers = new ArrayList<>();

  @ManyToMany(mappedBy = "classesStudents")
  List<Student> students = new ArrayList<>();

  @OneToMany(mappedBy = "classeAttendances", cascade = CascadeType.ALL)
  private List<Attendance> attendances = new ArrayList<>();

  public Classe() {
  }

  public Classe(String name, int year) {
    this.name = name;
    this.year = year;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getYear() {
    return year;
  }

  public void setYear(int year) {
    this.year = year;
  }

  public List<Teacher> getTeachers() {
    return teachers;
  }

  public void setTeachers(List<Teacher> teachers) {
    this.teachers = teachers;
  }

  public List<Student> getStudents() {
    return students;
  }

  public void setStudents(List<Student> students) {
    this.students = students;
  }

  public List<Attendance> getAttendances() {
    return attendances;
  }

  public void setAttendances(List<Attendance> attendances) {
    this.attendances = attendances;
  }
}
