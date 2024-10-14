package com.scholario.scholario_demo.controller;

import com.scholario.scholario_demo.dto.attendance.AttendanceCreationDto;
import com.scholario.scholario_demo.dto.attendance.AttendanceDto;
import com.scholario.scholario_demo.entiity.Attendance;
import com.scholario.scholario_demo.exception.attendance.AttendanceNotFoundException;
import com.scholario.scholario_demo.service.AttendanceService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * The type Attendance controller.
 */
@RestController
@RequestMapping("/attendances")
public class AttendanceController {

  private final AttendanceService attendanceService;

  /**
   * Instantiates a new Attendance controller.
   *
   * @param attendanceService the attendance service
   */
  @Autowired
  public AttendanceController(AttendanceService attendanceService) {
    this.attendanceService = attendanceService;
  }

  /**
   * Create attendance response entity.
   *
   * @param attendanceCreationDto the attendance creation dto
   * @param studentId             the student id
   * @param classId               the class id
   * @return the response entity
   */
  @PostMapping("{studentId}/{classId}")
  @PreAuthorize("hasAnyAuthority('admin', 'teacher')")
  public ResponseEntity<?> createAttendance(
      @RequestBody AttendanceCreationDto attendanceCreationDto,
      @PathVariable Long studentId, @PathVariable Long classId) {
    try {
      AttendanceDto attendanceDto = AttendanceDto
          .fromEntity(attendanceService.createAttendance(
              attendanceCreationDto.toEntity(), studentId, classId));
      return ResponseEntity.ok(attendanceDto);
    } catch (AttendanceNotFoundException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body("Attendance by Student with id " + attendanceCreationDto + " not found");
    }
  }


  /**
   * List all attendances response entity.
   *
   * @param pageNumber the page number
   * @param pageSize   the page size
   * @return the response entity
   */
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

  /**
   * List all attendances by student response entity.
   *
   * @param studentId the student id
   * @return the response entity
   */
  @GetMapping("student/{studentId}")
  public ResponseEntity<?> listAllAttendancesByStudent(@PathVariable Long studentId) {
    List<Attendance> attendances = attendanceService.listAllAttendancesByStudent(studentId);

    return ResponseEntity.ok(
        attendances.stream()
            .map(AttendanceDto::fromEntity)
            .toList()
    );
  }

  /**
   * List all attendances by class response entity.
   *
   * @param classId the class id
   * @return the response entity
   */
  @GetMapping("class/{classId}")
  public ResponseEntity<?> listAllAttendancesByClass(@PathVariable Long classId) {
    List<Attendance> attendances = attendanceService.listAllAttendancesByClass(classId);

    return ResponseEntity.ok(
        attendances.stream()
            .map(AttendanceDto::fromEntity)
            .toList()
    );
  }

  /**
   * Gets attendance by id.
   *
   * @param id the id
   * @return the attendance by id
   */
  @GetMapping("/{id}")
  public ResponseEntity<?> getAttendanceById(
      @PathVariable Long id) {
    try {
      AttendanceDto attendanceDto =
          AttendanceDto.fromEntity(attendanceService.getAttendanceById(id));
      return ResponseEntity.ok(attendanceDto);
    } catch (AttendanceNotFoundException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body("Attendance with id " + id + " not found.");
    }
  }

  /**
   * Update attendance response entity.
   *
   * @param id                    the id
   * @param attendanceCreationDto the attendance creation dto
   * @return the response entity
   */
  @PutMapping("/{id}")
  @PreAuthorize("hasAnyAuthority('admin', 'teacher')")
  public ResponseEntity<?> updateAttendance(
      @PathVariable Long id, @RequestBody AttendanceCreationDto attendanceCreationDto) {
    try {
      AttendanceDto attendanceDto = AttendanceDto.fromEntity(
          attendanceService.updateAttendance(id, attendanceCreationDto.toEntity()));
      return ResponseEntity.ok(attendanceDto);
    } catch (AttendanceNotFoundException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body("Attendance with id " + id + " not found");
    }
  }

  /**
   * Delete attendance response entity.
   *
   * @param id the id
   * @return the response entity
   */
  @DeleteMapping("/{id}")
  @PreAuthorize("hasAuthority('admin')")
  public ResponseEntity<?> deleteAttendance(@PathVariable Long id) {
    attendanceService.deleteAttendance(id);
    return ResponseEntity.ok("Attendance with id " + id + " deleted.");
  }
}
