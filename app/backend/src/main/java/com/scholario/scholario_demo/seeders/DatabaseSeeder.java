package com.scholario.scholario_demo.seeders;

import com.scholario.scholario_demo.entiity.Student;
import com.scholario.scholario_demo.entiity.Subject;
import com.scholario.scholario_demo.entiity.Teacher;
import com.scholario.scholario_demo.repository.StudentRepository;
import com.scholario.scholario_demo.repository.SubjectRepository;
import com.scholario.scholario_demo.repository.TeacherRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseSeeder implements CommandLineRunner {

  private final TeacherRepository teacherRepository;
  private final StudentRepository studentRepository;
  private final SubjectRepository subjectRepository;


  public DatabaseSeeder(TeacherRepository teacherRepository, StudentRepository studentRepository,
      SubjectRepository subjectRepository) {
    this.teacherRepository = teacherRepository;
    this.studentRepository = studentRepository;
    this.subjectRepository = subjectRepository;
  }

  @Override
  public void run(String... args) throws Exception {
    seedTeachers();
    seedStudents();
    seedSubjects();
  }

  private void seedTeachers() {
    List<Teacher> teachers = new ArrayList<>();
//    public Teacher(String name,
//        String email,
//        String password,
//        String role,
//        String phone,
//        String address,
//        String birthdate,
//        String department,
//        String hireDate )
    teachers.add(new Teacher(
        "Fernando Álisson",
        "@fernando.alisson@email.com",
        "fernaninho123",
        "Teacher",
        "81998979999",
        "Camocim de São Félix",
        "23/10/1999",
        "Linguagens",
        "01/01/2020"

    ));

  }

  private void seedStudents() {
    List<Student> students = new ArrayList<>();

    students.add(new Student(
        "Luiz Gustavo",
        "luizgustavo@email.com",
        "gustavo123",
        "Student",
        "81999896532",
        "Camocim de São Félix",
        "28/09/2006",
        1234565466L,
        "Nice",
        "81998979931"
    ));
  }

  private void seedSubjects() {
    List<Subject> subjects = new ArrayList<>();

    subjects.add(new Subject("Língua Inglesa","Linguagens códigos e suas tecnologias"));
    subjects.add(new Subject("Língua Espanhola", "Linguagens códigos e suas tecnologias"));
    subjects.add(new Subject("Língua Portuguesa", "Linguagens códigos e suas tecnologias"));
    subjects.add(new Subject("Matemática", "Exatas"));
    subjects.add(new Subject("Química", "Ciências da natureza"));
    subjects.add(new Subject("Biologia", "Ciências da natureza"));
    subjects.add(new Subject("Ciência", "Ciências da natureza"));
  }

  private TeacherRepository getTeacherRepository() {
    return teacherRepository;
  }


  private StudentRepository getStudentRepository() {
    return studentRepository;
  }

  private SubjectRepository getSubjectRepository() {
    return subjectRepository;
  }
}
