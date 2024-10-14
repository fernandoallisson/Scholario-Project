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
import lombok.Getter;
import lombok.Setter;


/**
 * The type Classe.
 */
@Setter
@Getter
@Entity
@Table(name = "classes")
public class Classe {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;
  private int year;

  /**
   * The Teachers.
   */
  @ManyToMany(mappedBy = "classesTeachers")
  List<Teacher> teachers = new ArrayList<>();

  /**
   * The Students.
   */
  @ManyToMany(mappedBy = "classesStudents")
  List<Student> students = new ArrayList<>();

  @OneToMany(mappedBy = "classeAttendances", cascade = CascadeType.ALL)
  private List<Attendance> attendances = new ArrayList<>();

  /**
   * Instantiates a new Classe.
   */
  public Classe() {
  }

  /**
   * Instantiates a new Classe.
   *
   * @param name the name
   * @param year the year
   */
  public Classe(String name, int year) {
    this.name = name;
    this.year = year;
  }

}
