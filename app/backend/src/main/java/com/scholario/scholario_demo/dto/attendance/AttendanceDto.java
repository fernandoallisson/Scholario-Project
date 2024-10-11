package com.scholario.scholario_demo.dto.attendance;


import com.scholario.scholario_demo.entiity.Attendance;

public record AttendanceDto(Long id, Long studentId, Long classId, String date, String status) {

    public static AttendanceDto fromEntity(Attendance attendance) {
        return new AttendanceDto(
            attendance.getId(),
            attendance.getStudentAttendances().getId(),
            attendance.getClasseAttendances().getId(),
            attendance.getDate(),
            attendance.getStatus());
    }
}
