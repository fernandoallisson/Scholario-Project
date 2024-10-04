package com.scholario.scholario_demo.service;

import com.scholario.scholario_demo.entiity.Student;
import com.scholario.scholario_demo.exception.student.StudentNotFoundException;
import com.scholario.scholario_demo.repository.StudentRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
  private final StudentRepository studentRepository;

  @Autowired
  public StudentService(StudentRepository studentRepository) {
    this.studentRepository = studentRepository;
  }

  public List<Student> getAllStudents() {
    return studentRepository.findAll();
  }

  public Student getStudentById(Long id) throws StudentNotFoundException {
    return studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException("USer not found."));
  }

  public Student createStudent(Student student) {
    return studentRepository.save(student);
  }

  public Student updateStudent(Long id, Student student) {
    Student studentFound = getStudentById(id);

    studentFound.setName(student.getName());
    studentFound.setEmail(student.getEmail());
    studentFound.setPassword(student.getPassword());
    studentFound.setRole(student.getRole());
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
}
