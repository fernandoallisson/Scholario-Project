package com.scholario.scholario_demo.repository;

import com.scholario.scholario_demo.entiity.Attendance;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Attendance repository.
 */
@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

  /**
   * Find all by student attendances id list.
   *
   * @param studentId the student id
   * @return the list
   */
  List<Attendance> findAllByStudentAttendancesId(Long studentId);

  /**
   * Find all by classe attendances id list.
   *
   * @param classId the class id
   * @return the list
   */
  List<Attendance> findAllByClasseAttendancesId(Long classId);

  /**
   * Find all by student attendances id and classe attendances id list.
   *
   * @param studentId the student id
   * @param classId   the class id
   * @return the list
   */
  List<Attendance> findAllByStudentAttendancesIdAndClasseAttendancesId(
      Long studentId, Long classId);

  /**
   * Find all by student attendances id and classe attendances id and date list.
   *
   * @param studentId the student id
   * @param classId   the class id
   * @param date      the date
   * @return the list
   */
  List<Attendance> findAllByStudentAttendancesIdAndClasseAttendancesIdAndDate(
      Long studentId, Long classId, String date);
  
}
