package com.scholario.scholario_demo.controller;

import com.scholario.scholario_demo.dto.attendance.AttendanceDto;
import com.scholario.scholario_demo.dto.attendance.AttendanceCreationDto;
import com.scholario.scholario_demo.exception.attendance.AttendanceNotFoundException;
import com.scholario.scholario_demo.service.AttendanceService;
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

}
