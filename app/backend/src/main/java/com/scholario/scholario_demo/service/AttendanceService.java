package com.scholario.scholario_demo.service;

import com.scholario.scholario_demo.entiity.Attendance;
import com.scholario.scholario_demo.entiity.Classe;
import com.scholario.scholario_demo.entiity.Student;
import com.scholario.scholario_demo.exception.attendance.AttendanceNotFoundException;
import com.scholario.scholario_demo.repository.AttendanceRepository;
import com.scholario.scholario_demo.repository.ClassRepository;
import com.scholario.scholario_demo.repository.StudentRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AttendanceService {

  private final AttendanceRepository attendanceRepository;
  private final StudentService studentService;
  private final ClassService classService;

  @Autowired
  public AttendanceService(AttendanceRepository attendanceRepository,
      StudentRepository studentRepository,
      ClassRepository classRepository,
      StudentService studentService,
      ClassService classService) {
    this.attendanceRepository = attendanceRepository;
    this.studentService = studentService;
    this.classService = classService;
  }

  public Attendance createAttendance(Attendance attendance, Long studentId, Long classId) {
    Student student = studentService.getStudentById(studentId);

    Classe classe = classService.getClassById(classId);

    if (student == null || classe == null) {
      return null;
    }

    attendance.setStudentAttendances(student);
    attendance.setClasseAttendances(classe);

    if (attendance.getStatus() == null) {
      attendance.setStatus("PENDING");
    }

    if (attendance.getDate() == null) {
      attendance.setDate(java.time.LocalDate.now().toString());
      
    }

    return attendanceRepository.save(attendance);
  }

  // GET /attendances?pageNumber=0&pageSize=20
  public List<Attendance> listAllAttendances(int pageNumber, int pageSize) {
    Pageable pageable = PageRequest.of(pageNumber, pageSize);
    return attendanceRepository.findAll(pageable).toList();
  }

  public List<Attendance> listAllAttendancesByStudent(Long studentId) {

    return attendanceRepository.findAllByStudentAttendancesId(studentId);
  }

  public List<Attendance> listAllAttendancesByClass(Long classId) {

    return attendanceRepository.findAllByClasseAttendancesId(classId);
  }

  public Attendance getAttendanceById(Long id) throws AttendanceNotFoundException {
    return attendanceRepository.findById(id).orElseThrow(
        () -> new AttendanceNotFoundException("Attendance not found."));
  }

  // Método para atualizar uma presença

  public Attendance updateAttendance(Long id, Attendance attendance) throws AttendanceNotFoundException {
    Attendance existingAttendance = attendanceRepository.findById(id).orElseThrow(
        () -> new AttendanceNotFoundException("Attendance not found."));

    existingAttendance.setStatus(attendance.getStatus());
    existingAttendance.setDate(attendance.getDate());

    return attendanceRepository.save(existingAttendance);
  }

  // Método para deletar uma presença

  public void deleteAttendance(Long id) {
    attendanceRepository.deleteById(id);
  }
}
