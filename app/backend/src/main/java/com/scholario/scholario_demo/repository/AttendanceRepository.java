package com.scholario.scholario_demo.repository;

import com.scholario.scholario_demo.entiity.Attendance;


import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
}
