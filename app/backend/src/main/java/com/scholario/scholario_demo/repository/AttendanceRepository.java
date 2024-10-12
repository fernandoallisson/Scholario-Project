package com.scholario.scholario_demo.repository;

import com.scholario.scholario_demo.entiity.Attendance;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

  List<Attendance> findAllByStudentAttendancesId(Long studentId);

  List<Attendance> findAllByClasseAttendancesId(Long classId);

  List<Attendance> findAllByStudentAttendancesIdAndClasseAttendancesId(Long studentId, Long classId);

  List<Attendance> findAllByStudentAttendancesIdAndClasseAttendancesIdAndDate(Long studentId, Long classId, String date);
  
}
