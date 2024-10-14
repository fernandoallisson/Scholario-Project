package com.scholario.scholario_demo.entiity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * The type Parent.
 */
@Entity
@Getter
@Setter
@Table(name = "parents")
public class Parent {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "parent_seq")
  @SequenceGenerator(name = "parent_seq", sequenceName = "parent_sequence", initialValue = 2234568, allocationSize = 1)
  private Long id;

  // Father
  @Column(unique = true)
  private String fatherCpfNumber;

  @Column(unique = true)
  private String fatherEmail;

  private String fatherName;
  private String fatherAddress;
  private String fatherProfession;
  private String fatherNumberPhone;
  private String fatherBirth;
  private String fatherMaritalStatus;

  // Mother
  @Column(unique = true)
  private String motherCpfNumber;

  @Column(unique = true)
  private String motherEmail;

  private String motherName;
  private String motherAddress;
  private String motherProfession;
  private String motherNumberPhone;
  private String motherBirth;
  private String motherMaritalStatus;

  // Children
  @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
  private List<Student> children = new ArrayList<>();

  /**
   * Instantiates a new Parent.
   */
  public Parent() {
  }

  /**
   * Instantiates a new Parent.
   *
   * @param fatherName          the father name
   * @param fatherCpfNumber     the father cpf number
   * @param fatherRgNumber      the father rg number
   * @param fatherAddress       the father address
   * @param fatherProfession    the father profession
   * @param fatherNumberPhone   the father number phone
   * @param fatherBirth         the father birth
   * @param fatherMaritalStatus the father marital status
   * @param fatherEmail         the father email
   * @param motherName          the mother name
   * @param motherCpfNumber     the mother cpf number
   * @param motherRgNumber      the mother rg number
   * @param motherAddress       the mother address
   * @param motherProfession    the mother profession
   * @param motherNumberPhone   the mother number phone
   * @param motherBirth         the mother birth
   * @param motherMaritalStatus the mother marital status
   * @param motherEmail         the mother email
   * @param children            the children
   */
  public Parent(String fatherName, String fatherCpfNumber,
      String fatherAddress, String fatherProfession, String fatherNumberPhone, String fatherBirth,
      String fatherMaritalStatus, String fatherEmail, String motherName, String motherCpfNumber,
      String motherAddress, String motherProfession,
      String motherNumberPhone,
      String motherBirth, String motherMaritalStatus, String motherEmail, List<Student> children) {
    this.fatherName = fatherName;
    this.fatherCpfNumber = fatherCpfNumber;
    this.fatherAddress = fatherAddress;
    this.fatherProfession = fatherProfession;
    this.fatherNumberPhone = fatherNumberPhone;
    this.fatherBirth = fatherBirth;
    this.fatherMaritalStatus = fatherMaritalStatus;
    this.fatherEmail = fatherEmail;
    this.motherName = motherName;
    this.motherCpfNumber = motherCpfNumber;
    this.motherAddress = motherAddress;
    this.motherProfession = motherProfession;
    this.motherNumberPhone = motherNumberPhone;
    this.motherBirth = motherBirth;
    this.motherMaritalStatus = motherMaritalStatus;
    this.motherEmail = motherEmail;
    this.children = children;
  }
}
