package com.scholario.scholario_demo.service;

import com.scholario.scholario_demo.entiity.Subject;
import com.scholario.scholario_demo.entiity.Teacher;
import com.scholario.scholario_demo.exception.teacher.TeacherNotFoundException;
import com.scholario.scholario_demo.repository.TeacherRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherService {
  private final TeacherRepository teacherRepository;
  private final SubjectService subjectService;

  @Autowired
  public TeacherService (TeacherRepository teacherRepository, SubjectService subjectService) {
    this.teacherRepository = teacherRepository;
    this.subjectService = subjectService;
  }

  public List<Teacher> getAllTeachers() {
    return teacherRepository.findAll();
  }

  public Teacher getTeacherById(Long id) throws TeacherNotFoundException {
    return teacherRepository.findById(id).orElseThrow(() -> new TeacherNotFoundException("Teacher not found"));
  }

  public Teacher createTeacher(Teacher teacher) {
    return teacherRepository.save(teacher);
  }

  public Teacher updateTeacher(Long id, Teacher teacher) {
    Teacher teacherFound = new Teacher();

    teacherFound.setName(teacher.getName());
    teacherFound.setEmail(teacher.getEmail());
    teacherFound.setDepartment(teacher.getDepartment());
    teacherFound.setAddress(teacher.getAddress());
    teacherFound.setBirthdate(teacher.getBirthdate());
    teacherFound.setPassword(teacher.getPassword());
    teacherFound.setPhone(teacher.getPhone());
    teacherFound.setRole(teacher.getRole());
    teacherFound.setSubject(teacher.getSubject());
    teacherFound.setHireDate(teacher.getHireDate());

    return teacherRepository.save(teacherFound);
  }

  public void deleteTeacher (Long id) {
    teacherRepository.deleteById(id);
  }

  // Relacionar um professor a uma disciplina específica ------------------------------- (N:N)

  public Teacher addSubjectToTeacher(Long teacherId, Long subjectId) throws TeacherNotFoundException {
    Teacher teacher = getTeacherById(teacherId);
    Subject subject = subjectService.getSubjectById(subjectId);

    teacher.getSubject().add(subject);
    return teacherRepository.save(teacher);
  }

  public Teacher removeSubjectFromTeacher(Long teacherId, Long subjectId) throws TeacherNotFoundException {
    Teacher teacher = getTeacherById(teacherId);
    Subject subject = subjectService.getSubjectById(subjectId);

    teacher.getSubject().remove(subject);
    return teacherRepository.save(teacher);
  }
}
