package com.scholario.scholario_demo.entiity;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * The type Subject.
 */
@Setter
@Getter
@Entity
@Table(name = "subjects")
public class Subject {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "subject_seq")
  @SequenceGenerator(name = "subject_seq", sequenceName = "subject_sequence", initialValue = 4234568, allocationSize = 1)
  private Long id;

  private String name;
  private String description;

  @ManyToMany(mappedBy = "subject", cascade = CascadeType.ALL)
  private List<Teacher> teachers = new ArrayList<>();

  @OneToMany(mappedBy = "subjectGrades", cascade = CascadeType.ALL)
  private List<Grade> grades = new ArrayList<>();

  @ManyToMany(mappedBy = "subjectClasses", cascade = CascadeType.ALL)
  private List<Classe> classes = new ArrayList<>();


  /**
   * Instantiates a new Subject.
   */
  public Subject() {
  }

  /**
   * Instantiates a new Subject.
   *
   * @param name        the name
   * @param description the description
   */
  public Subject(String name, String description) {
    this.name = name;
    this.description = description;
  }

}
