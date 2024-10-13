package com.scholario.scholario_demo.service;

import com.scholario.scholario_demo.entiity.Classe;
import com.scholario.scholario_demo.entiity.Subject;
import com.scholario.scholario_demo.entiity.Teacher;
import com.scholario.scholario_demo.exception.classes.ClassNotFoundException;
import com.scholario.scholario_demo.exception.subject.SubjectNotFoundException;
import com.scholario.scholario_demo.exception.teacher.TeacherNotFoundException;
import com.scholario.scholario_demo.repository.TeacherRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class TeacherService {
  private final TeacherRepository teacherRepository;
  private final SubjectService subjectService;
  private final ClassService classService;

  @Autowired
  public TeacherService (TeacherRepository teacherRepository, SubjectService subjectService,
      ClassService classService) {
    this.teacherRepository = teacherRepository;
    this.subjectService = subjectService;
    this.classService = classService;
  }

  public List<Teacher> getAllTeachers(int pageNumber, int pageSize) {
    Pageable pageable = PageRequest.of(pageNumber, pageSize);

    Page<Teacher> teacherPage = teacherRepository.findAll(pageable);

    return teacherPage.toList();
  }

  public Teacher getTeacherById(Long id) throws TeacherNotFoundException {
    return teacherRepository.findById(id).orElseThrow(() -> new TeacherNotFoundException("Teacher not found"));
  }

  public Teacher createTeacher(Teacher teacher) {
    String hashedPassword = new BCryptPasswordEncoder()
        .encode(teacher.getPassword());

    teacher.setPassword(hashedPassword);
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
    teacherFound.setSubject(teacher.getSubject());
    teacherFound.setHireDate(teacher.getHireDate());

    return teacherRepository.save(teacherFound);
  }

  public void deleteTeacher (Long id) {
    teacherRepository.deleteById(id);
  }

  // Relacionar um professor a uma disciplina específica ------------------------------- (N:N)

  public Teacher addSubjectToTeacher(Long teacherId, Long subjectId) throws TeacherNotFoundException, SubjectNotFoundException {
    Teacher teacher = getTeacherById(teacherId);
    Subject subject = subjectService.getSubjectById(subjectId);

    if (teacher.getSubject().contains(subject)) {
      return teacher;
    }

    teacher.getSubject().add(subject);
    return teacherRepository.save(teacher);
  }

  public Teacher removeSubjectFromTeacher(Long teacherId, Long subjectId) throws TeacherNotFoundException, SubjectNotFoundException {
    Teacher teacher = getTeacherById(teacherId);
    Subject subject = subjectService.getSubjectById(subjectId);

    teacher.getSubject().remove(subject);
    return teacherRepository.save(teacher);
  }


  // Relacionar um professor a uma turma específica ------------------------------- (N:N)

  public Teacher addClassToTeacher(Long teacherId, Long classId) throws TeacherNotFoundException, ClassNotFoundException {
    Teacher teacher = getTeacherById(teacherId);
    Classe classe = classService.getClassById(classId);

    if (teacher.getClassesTeachers().contains(classe)) {
      return teacher;
    }

    teacher.getClassesTeachers().add(classe);
    return teacherRepository.save(teacher);
  }

  public Teacher removeClassFromTeacher(Long teacherId, Long classId) throws TeacherNotFoundException, ClassNotFoundException {
    Teacher teacher = getTeacherById(teacherId);
    Classe classe = classService.getClassById(classId);

    teacher.getClassesTeachers().remove(classe);
    classe.getTeachers().remove(teacher);
    return teacherRepository.save(teacher);
  }

  // Extra methods

  public List<Teacher> getTeacherBySubjectId(Long subjectId) {
    return teacherRepository.findBySubjectId(subjectId);
  }

  public List<Teacher> getTeacherByName(String name) {
    return teacherRepository.findByNameContaining(name);
  }

  //  Security
  public UserDetails loadTeacherByEmail(String email) throws UsernameNotFoundException {
    return teacherRepository.findByEmail(email).orElseThrow(
        () -> new UsernameNotFoundException(email)
    );
  }
}
