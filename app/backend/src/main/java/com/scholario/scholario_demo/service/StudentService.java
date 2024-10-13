package com.scholario.scholario_demo.service;

import com.scholario.scholario_demo.entiity.Classe;
import com.scholario.scholario_demo.entiity.Student;
import com.scholario.scholario_demo.exception.student.StudentNotFoundException;
import com.scholario.scholario_demo.repository.StudentRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
  private final StudentRepository studentRepository;
  private final ClassService classService;

  @Autowired
  public StudentService(StudentRepository studentRepository, ClassService classService) {
    this.studentRepository = studentRepository;
    this.classService = classService;
  }

  public List<Student> getAllStudents(int pageNumber, int pageSize) {

    Pageable pageable = PageRequest.of(pageNumber, pageSize);
    Page<Student> studentPage = studentRepository.findAll(pageable);

    return studentPage.toList();
  }

  public Student getStudentById(Long id) throws StudentNotFoundException {
    return studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException("User not found."));
  }

  public Student createStudent(Student student) {

    String hashedPassword = new BCryptPasswordEncoder()
        .encode(student.getPassword());

    student.setPassword(hashedPassword);

    // Chamar método para verificação antes de salvar

    return studentRepository.save(student);
  }

  public Student updateStudent(Long id, Student student) {
    Student studentFound = getStudentById(id);

    studentFound.setName(student.getName());
    studentFound.setEmail(student.getEmail());
    studentFound.setPassword(student.getPassword());
    studentFound.setPhone(student.getPhone());
    studentFound.setAddress(student.getAddress());
    studentFound.setBirthdate(student.getBirthdate());
    studentFound.setEnrollment(student.getEnrollment());
    studentFound.setGuardianName(student.getGuardianName());
    studentFound.setGuardianCellPhone(student.getGuardianCellPhone());

    return studentRepository.save(studentFound);
  }

  public void deleteStudent(Long id) throws StudentNotFoundException {
    Student student = getStudentById(id);

    studentRepository.delete(student);
  }


  // Relacionar um estudante a uma classe -------------- N:N
  public Student addClassToStudent(Long studentId, Long classId) {
    Student student = getStudentById(studentId);
    Classe classe = classService.getClassById(classId);

    if (student.getClassesStudents().contains(classe)) {
      return student;
    }

    student.getClassesStudents().add(classe);

    return studentRepository.save(student);
    
  }

  public Student removeClassFromStudent(Long studentId, Long classId) {
    Student student = getStudentById(studentId);
    Classe classe = classService.getClassById(classId);

   student.getClassesStudents().remove(classe);
   return studentRepository.save(student);
  }

  // Extra methods -----------------------------------------------------
  public List<Student> getStudentByClassId(Long classId) {
    return studentRepository.findByClassId(classId);
  }

  public List<Student> getStudentsByName (String name) {
    return studentRepository.findByNameContaining(name);
  }

  //  Security

  public UserDetails loadStudentByEmail(String email) throws UsernameNotFoundException {
    return studentRepository.findByEmail(email).orElseThrow(
        () -> new UsernameNotFoundException(email)
    );
  }
}
