package com.scholario.scholario_demo.dto.attendance;

import com.scholario.scholario_demo.entiity.Attendance;

/**
 * The type Attendance creation dto.
 */
public record AttendanceCreationDto(
    String date, String status) {

  /**
   * To entity attendance.
   *
   * @return the attendance
   */
  public Attendance toEntity() {
    return new Attendance(date, status);
  }
}