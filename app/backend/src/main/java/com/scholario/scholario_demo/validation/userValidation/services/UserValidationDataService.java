package com.scholario.scholario_demo.validation.userValidation.services;

import com.scholario.scholario_demo.entiity.Administrator;
import com.scholario.scholario_demo.entiity.Student;
import com.scholario.scholario_demo.entiity.Teacher;
import com.scholario.scholario_demo.repository.UserRepository;
import com.scholario.scholario_demo.validation.userValidation.exception.DataIntegrityException;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type User validation data service.
 */
@Service
public class UserValidationDataService {

  private final UserRepository userRepository;

  // Rules for validation
  private static final String NAME_REGEX = "^[A-Za-zÀ-ÖØ-öø-ÿ\\s'-]{2,50}$";
  private static final String EMAIL_REGEX = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,6}$";
  private static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*["
      + "@#$%^&+=])(?=\\S+$).{8,}$";
  private static final String PHONE_REGEX = "^[0-9]{10,11}$";
  private static final String ADDRESS_REGEX = "^[A-Za-zÀ-ÖØ-öø-ÿ0-9 ,'#.-]{5,100}$";
  private static final String BIRTHDATE_REGEX = "^[0-9]{4}-[0-9]{2}-[0-9]{2}$";
  private static final List<String> COLOR_RACE_REGEX = List.of("branco", "preto", "pardo",
      "amarelo", "indígena");
  private static final List<String> BLOOD_TYPE_REGEX = List.of("A+", "A-", "B+", "B-", "AB+",
      "AB-", "O+", "O-");
  private static final List<String> NATIONALITY_REGEX = List.of("brasileiro", "estrangeiro");
  private static final List<String> SEX_REGEX = List.of("masculino", "feminino");

  private static final List<String> ACADEMIC_DEPARTMENTS = List.of(
      "Matemática",
      "Física",
      "Química",
      "Biologia",
      "História",
      "Geografia",
      "Filosofia",
      "Sociologia",
      "Letras",
      "Educação Física",
      "Artes",
      "Música",
      "Informática",
      "Engenharia",
      "Administração",
      "Direito",
      "Medicina",
      "Enfermagem",
      "Psicologia",
      "Economia"
  );

  private static final List<String> ADMIN_DEPARTMENTS = List.of(
      "Recursos Humanos",
      "Finanças",
      "Contabilidade",
      "Secretaria Acadêmica",
      "Tecnologia da Informação",
      "Planejamento e Desenvolvimento",
      "Serviços Gerais",
      "Infraestrutura e Manutenção",
      "Comunicação e Marketing",
      "Biblioteca",
      "Relações Internacionais",
      "Compras e Suprimentos",
      "Transporte",
      "Segurança",
      "Assessoria Jurídica",
      "Registro Escolar",
      "Coordenação Pedagógica",
      "Almoxarifado",
      "Diretoria",
      "Patrimônio"
  );
  /**
   * Instantiates a new User validation data service.
   *
   * @param userRepository the user repository
   */
  @Autowired
  public UserValidationDataService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  /**
   * Validate student.
   *
   * @param student the student
   */
  // validate methods for each entity by user type
  public void validateStudent(Student student) {
    validateName(student.getName());
    validateEmail(student.getEmail());
    validatePassword(student.getPassword());
    validatePhone(student.getPhone());
    validateAddress(student.getAddress());
    validateBirthdate(student.getBirthdate());
    validateColorRace(student.getColorRace());
    validateBloodType(student.getBloodType());
    validateNationality(student.getNationality());
    validateSex(student.getSex());
    validateCpfNumber(student.getCpfNumber());
  }

  /**
   * Validate teacher.
   *
   * @param teacher the teacher
   */
  public void validateTeacher(Teacher teacher) {
    validateName(teacher.getName());
    validateEmail(teacher.getEmail());
    validatePassword(teacher.getPassword());
    validatePhone(teacher.getPhone());
    validateAddress(teacher.getAddress());
    validateBirthdate(teacher.getBirthdate());
    validateColorRace(teacher.getColorRace());
    validateBloodType(teacher.getBloodType());
    validateNationality(teacher.getNationality());
    validateSex(teacher.getSex());
    validateCpfNumber(teacher.getCpfNumber());
    validateDepartment(teacher.getDepartment());
    validateHireDate(teacher.getHireDate());
  }

  /**
   * Validate admin.
   *
   * @param administrator the administrator
   */
  public void validateAdmin(Administrator administrator) {
    validateName(administrator.getName());
    validateEmail(administrator.getEmail());
    validatePassword(administrator.getPassword());
    validatePhone(administrator.getPhone());
    validateAddress(administrator.getAddress());
    validateBirthdate(administrator.getBirthdate());
    validateColorRace(administrator.getColorRace());
    validateBloodType(administrator.getBloodType());
    validateNationality(administrator.getNationality());
    validateSex(administrator.getSex());
    validateCpfNumber(administrator.getCpfNumber());
    validateDepartmentAdm(administrator.getDepartment());
    validateHireDate(administrator.getHireDate());
  }

  // Validation methods
  private void validateName(String name) {
    if (name == null || name.isEmpty()) {
      throw new DataIntegrityException("Nome não pode ser vazio. Ex: João Silva");
    }
    if (!name.matches(NAME_REGEX)) {
      throw new DataIntegrityException("Nome inválido. Ex: João Silva");
    }
  }

  private void validateEmail(String email) {
    if (email == null || email.isEmpty()) {
      throw new DataIntegrityException("Email não pode ser vazio. Ex: mail@email.com");
    }
    if (!email.matches(EMAIL_REGEX)) {
      throw new DataIntegrityException("Email inválido. Ex: mail@email.com");
    }
    if (userRepository.findByEmail(email).isPresent()) {
      throw new DataIntegrityException("Email já cadastrado.");
    }
  }

  private void validatePassword(String password) {
    if (password == null || password.isEmpty()) {
      throw new DataIntegrityException(
          "Senha não pode ser vazia. Deve conter ao menos "
              + "8 caracteres, uma letra maiúscula, "
              + "uma letra minúscula, um número e um caractere especial.");
    }
    if (!password.matches(PASSWORD_REGEX)) {
      throw new DataIntegrityException(
          "Senha inválida. Deve conter ao menos 8 caracteres, "
              + "uma letra maiúscula, uma letra minúscula, um número e um caractere especial.");
    }
  }

  private void validatePhone(String phone) {
    if (phone == null || phone.isEmpty()) {
      throw new DataIntegrityException("Telefone não pode ser vazio. Ex: 11999999999");
    }
    if (!phone.matches(PHONE_REGEX)) {
      throw new DataIntegrityException("Telefone inválido. Ex: 11999999999");
    }
  }

  private void validateAddress(String address) {
    if (address == null || address.isEmpty()) {
      throw new DataIntegrityException("Endereço não pode ser vazio. Ex: Rua dos Bobos, 0");
    }
    if (!address.matches(ADDRESS_REGEX)) {
      throw new DataIntegrityException(
          "Endereço inválido. Ex: Rua dos Bobos, 0");
    }
  }

  private void validateBirthdate(String birthdate) {
    if (birthdate == null || birthdate.isEmpty()) {
      throw new DataIntegrityException(
          "Data de nascimento não pode ser vazia. Ex: 1999-12-31. Formato: yyyy-MM-dd");
    }
    if (!birthdate.matches(BIRTHDATE_REGEX)) {
      throw new DataIntegrityException(
          "Data de nascimento inválida. Ex: 1999-12-31. Formato: yyyy-MM-dd");
    }
  }

  private void validateColorRace(String colorRace) {
    if (colorRace == null || colorRace.isEmpty()) {
      throw new DataIntegrityException(
          "Cor/Raça não pode ser vazia. Ex: Branco, Preto, Pardo, Amarelo, Indígena");
    }
    if (!COLOR_RACE_REGEX
        .contains(colorRace.toLowerCase())) {
      throw new DataIntegrityException(
          "Cor/Raça inválida. Ex: Branco, Preto, Pardo, Amarelo, Indígena");
    }
  }

  private void validateBloodType(String bloodType) {
    if (bloodType == null || bloodType.isEmpty()) {
      throw new DataIntegrityException(
          "Tipo sanguíneo não pode ser vazio. Ex: A+, A-, B+, B-, AB+, AB-, O+, O-\"");
    }
    if (!BLOOD_TYPE_REGEX
        .contains(bloodType.toUpperCase())) {
      throw new DataIntegrityException(
          "Tipo sanguíneo inválido. Ex: A+, A-, B+, B-, AB+, AB-, O+, O-");
    }
  }

  private void validateNationality(String nationality) {
    if (nationality == null || nationality.isEmpty()) {
      throw new DataIntegrityException("Nacionalidade não pode ser vazia.");
    }

    if (!NATIONALITY_REGEX
        .contains(nationality.toLowerCase())) {
      throw new DataIntegrityException("Nacionalidade inválida. Ex: Brasileiro, Estrangeiro");
    }
  }

  private void validateSex(String sex) {
    if (sex == null || sex.isEmpty()) {
      throw new DataIntegrityException("Sexo não pode ser vazio. Ex: Masculino, Feminino");
    }

    if (!SEX_REGEX.contains(sex.toLowerCase())) {
      throw new DataIntegrityException("Sexo inválido. Ex: Masculino, Feminino");
    }
  }

  private void validateCpfNumber(String cpfNumber) {
    if (cpfNumber == null || cpfNumber.isEmpty()) {
      throw new DataIntegrityException("CPF não pode ser vazio.");
    }
    if (userRepository.findByCpfNumber(cpfNumber).isPresent()) {
      throw new DataIntegrityException("CPF já cadastrado. Ex: 12345678901");
    }
  }

  private void validateDepartment(String department) {
    if (department == null || department.isEmpty()) {
      throw new DataIntegrityException(
          "Departamento não pode ser vazio. Ex: Matemática, Física, Química");
    }

    department = department.trim();
    if (!ACADEMIC_DEPARTMENTS.contains(department)) {
      throw new DataIntegrityException(
          "Departamento inválido. Ex: Matemática, Física, Química");
    }
  }

  private void validateHireDate(String hireDate) {
    if (hireDate == null || hireDate.isEmpty()) {
      throw new DataIntegrityException(
          "Data de contratação não pode ser vazia. Ex: 1999-12-31. Formato: yyyy-MM-dd");
    }
    if (!hireDate.matches(BIRTHDATE_REGEX)) {
      throw new DataIntegrityException(
          "Data de contratação inválida. Ex: 1999-12-31. Formato: yyyy-MM-dd");
    }
  }

  private void validateDepartmentAdm(String department) {
    if (department == null || department.isEmpty()) {
      throw new DataIntegrityException(
          "Departamento não pode ser vazio. Ex: Recursos Humanos, Finanças, Contabilidade");
    }

    department = department.trim();
    if (!ADMIN_DEPARTMENTS.contains(department)) {
      throw new DataIntegrityException(
          "Departamento inválido. Ex: Recursos Humanos, Finanças, Contabilidade");
    }
  }
}
