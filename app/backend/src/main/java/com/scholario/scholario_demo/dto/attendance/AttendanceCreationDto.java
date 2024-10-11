package com.scholario.scholario_demo.dto.attendance;

import com.scholario.scholario_demo.entiity.Attendance;

public record AttendanceCreationDto(
    String date, String status) {
  public Attendance toEntity() {
    return new Attendance(date, status);
  }
}