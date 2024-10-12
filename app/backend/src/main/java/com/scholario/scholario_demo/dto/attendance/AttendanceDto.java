package com.scholario.scholario_demo.dto.attendance;


import com.scholario.scholario_demo.entiity.Attendance;

public record AttendanceDto(Long id, Long studentId, String name, Long classId, String className, String date, String status) {

    public static AttendanceDto fromEntity(Attendance attendance) {
        return new AttendanceDto(
            attendance.getId(),
            attendance.getStudentAttendances().getId(),
            attendance.getStudentAttendances().getName(),
            attendance.getClasseAttendances().getId(),
            attendance.getClasseAttendances().getName(),
            attendance.getDate(),
            attendance.getStatus());
    }
}
