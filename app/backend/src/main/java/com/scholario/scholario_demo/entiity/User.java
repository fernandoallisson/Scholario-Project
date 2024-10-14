package com.scholario.scholario_demo.entiity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.util.Collection;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * The type User.
 * 
 */
@Setter
@Getter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "user_type")
@Table(name = "users")
public class User implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
  @SequenceGenerator(name = "user_seq", sequenceName = "user_sequence", initialValue = 1234567, allocationSize = 1)
  private Long id;

  @Column(unique = true)
  private String email;

  @Column(unique = true)
  private String cpfNumber;

  private String name;
  private String password;
  private String phone;
  private String address;
  private String birthdate;
  private String colorRace;
  private List<String> disabilities;
  private String bloodType;
  private String nationality;
  private List<String> specialConditions;
  private List<String> allergiesList;
  private String sex;


  private String image;

  @Column(name = "user_type", insertable = false, updatable = false)
  private String userType;

  /**
   * Instantiates a new User.
   */
  public User() {
  }

  /**
   * Instantiates a new User.
   *
   * @param name      the name
   * @param email     the email
   * @param password  the password
   * @param phone     the phone
   * @param address   the address
   * @param birthdate the birthdate
   */
  public User(String name, String email, String password, String phone, String address,
      String birthdate, String colorRace, List<String> disabilities, String bloodType,
      String nationality, List<String> specialConditions, List<String> allergiesList, String sex,
      String cpfNumber, String image) {
    this.name = name;
    this.email = email;
    this.password = password;
    this.phone = phone;
    this.address = address;
    this.birthdate = birthdate;
    this.colorRace = colorRace;
    this.disabilities = disabilities;
    this.bloodType = bloodType;
    this.nationality = nationality;
    this.specialConditions = specialConditions;
    this.allergiesList = allergiesList;
    this.sex = sex;
    this.cpfNumber = cpfNumber;
    this.image = image;
  }

  // For Security

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority(userType));
  }

  @Override
  public String getUsername() {
    return this.email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return UserDetails.super.isAccountNonExpired();
  }

  @Override
  public boolean isAccountNonLocked() {
    return UserDetails.super.isAccountNonLocked();
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return UserDetails.super.isCredentialsNonExpired();
  }

  @Override
  public boolean isEnabled() {
    return UserDetails.super.isEnabled();
  }
}
