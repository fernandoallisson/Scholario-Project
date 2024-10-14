package com.scholario.scholario_demo.entiity;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
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
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "class_seq")
  @SequenceGenerator(name = "class_seq", sequenceName = "class_sequence", initialValue = 3234567, allocationSize = 1)
  private Long id;

  private String name;
  private String shift;
  private int year;
  private boolean isActive;

  @ManyToMany
  @JoinTable(
      name = "classes_subjects",
      joinColumns = @JoinColumn(name = "classe_id"),
      inverseJoinColumns = @JoinColumn(name = "subject_id")
  )
  private List<Subject> subjectClasses = new ArrayList<>();

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
   * @param shift the shift
   */
  public Classe(String name, String shift, int year) {
    this.name = name;
    this.year = year;
    this.shift = shift;
  }

}
