package com.scholario.scholario_demo.service;

import com.scholario.scholario_demo.entiity.Attendance;
import com.scholario.scholario_demo.entiity.Classe;
import com.scholario.scholario_demo.entiity.Student;
import com.scholario.scholario_demo.exception.classes.ClassNotFoundException;
import com.scholario.scholario_demo.exception.student.StudentNotFoundException;
import com.scholario.scholario_demo.repository.AttendanceRepository;
import com.scholario.scholario_demo.repository.ClassRepository;
import com.scholario.scholario_demo.repository.StudentRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttendanceService {

  private final AttendanceRepository attendanceRepository;
  private final StudentRepository studentRepository;
  private final ClassRepository classRepository;
  private final StudentService studentService;
  private final ClassService classService;

  @Autowired
  public AttendanceService(AttendanceRepository attendanceRepository,
      StudentRepository studentRepository,
      ClassRepository classRepository,
      StudentService studentService,
      ClassService classService) {
    this.attendanceRepository = attendanceRepository;
    this.studentRepository = studentRepository;
    this.classRepository = classRepository;
    this.studentService = studentService;
    this.classService = classService;
  }

  // Método para registo de presença de um estudante com a sua classe
  public Attendance createAttendance(Attendance attendance, Long studentId, Long classId) {
    Student student = studentService.getStudentById(studentId);

    Classe classe = classService.getClassById(classId);

    if (student == null || classe == null) {
      return null;
    }

    // Se a data de presença já existir, não é possível registar a presença

    attendance.setStudentAttendances(student);
    attendance.setClasseAttendances(classe);

    return attendanceRepository.save(attendance);
  }

  // Método para listar todas as presenças registadas
  public List<Attendance> listAllAttendances() {
    return attendanceRepository.findAll();
  }

}
