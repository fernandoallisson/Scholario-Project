package com.scholario.scholario_demo.controller;

import com.scholario.scholario_demo.dto.attendance.AttendanceDto;
import com.scholario.scholario_demo.dto.attendance.AttendanceCreationDto;
import com.scholario.scholario_demo.entiity.Attendance;
import com.scholario.scholario_demo.exception.attendance.AttendanceNotFoundException;
import com.scholario.scholario_demo.service.AttendanceService;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/attendances")
public class AttendanceController {

  private final AttendanceService attendanceService;

  @Autowired
  public AttendanceController(AttendanceService attendanceService) {
    this.attendanceService = attendanceService;
  }

  @PostMapping("{studentId}/{classId}")
  public ResponseEntity<?> createAttendance(@RequestBody AttendanceCreationDto attendanceCreationDto,
      @PathVariable Long studentId, @PathVariable Long classId) {
    try {
      AttendanceDto attendanceDto = AttendanceDto
          .fromEntity(attendanceService.createAttendance(attendanceCreationDto.toEntity(), studentId, classId));
      return ResponseEntity.ok(attendanceDto);
    } catch (AttendanceNotFoundException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body("Attendance by Student with id " + attendanceCreationDto + " not found");
    }
  }


  @GetMapping
  public ResponseEntity<?> listAllAttendances(
      @RequestParam(required = false, defaultValue = "0") int pageNumber,
      @RequestParam(required = false, defaultValue = "20") int pageSize
  ) {

    List<Attendance> attendances = attendanceService.listAllAttendances(pageNumber, pageSize);

    return ResponseEntity.ok(
        attendances.stream()
            .map(AttendanceDto::fromEntity)
            .toList()
    );
  }

  @GetMapping("student/{studentId}")
  public ResponseEntity<?> listAllAttendancesByStudent(@PathVariable Long studentId) {
    List<Attendance> attendances = attendanceService.listAllAttendancesByStudent(studentId);

    return ResponseEntity.ok(
        attendances.stream()
            .map(AttendanceDto::fromEntity)
            .toList()
    );
  }

  @GetMapping("class/{classId}")
  public ResponseEntity<?> listAllAttendancesByClass(@PathVariable Long classId) {
    List<Attendance> attendances = attendanceService.listAllAttendancesByClass(classId);

    return ResponseEntity.ok(
        attendances.stream()
            .map(AttendanceDto::fromEntity)
            .toList()
    );
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> getAttendanceById(@PathVariable Long id) {
    try {
      AttendanceDto attendanceDto = AttendanceDto.fromEntity(attendanceService.getAttendanceById(id));
      return ResponseEntity.ok(attendanceDto);
    } catch (AttendanceNotFoundException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Attendance with id " + id + " not found.");
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> updateAttendance(@PathVariable Long id, @RequestBody AttendanceCreationDto attendanceCreationDto) {
    try {
      AttendanceDto attendanceDto = AttendanceDto.fromEntity(attendanceService.updateAttendance(id, attendanceCreationDto.toEntity()));
      return ResponseEntity.ok(attendanceDto);
    } catch (AttendanceNotFoundException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Attendance with id " + id + " not found");
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteAttendance(@PathVariable Long id) {
    attendanceService.deleteAttendance(id);
    return ResponseEntity.ok("Attendance with id " + id + " deleted.");
  }
}
