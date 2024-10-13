package com.scholario.scholario_demo.service;

import com.scholario.scholario_demo.entiity.User;
import com.scholario.scholario_demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

  private final AdministratorService administratorService;
  private final StudentService studentService;
  private final TeacherService teacherService;
  private final UserRepository userRepository;

  @Autowired
  public CustomUserDetailsService(AdministratorService administratorService,
      StudentService studentService, TeacherService teacherService, UserRepository userRepository) {
    this.administratorService = administratorService;
    this.studentService = studentService;
    this.teacherService = teacherService;
    this.userRepository = userRepository;
  }


  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    User user = userRepository.findByEmail(username)
        .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado."));

    System.out.println("EMAILLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL " + username);
    System.out.println("TIPOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO " + user.getUserType());

    return switch (user.getUserType()) {
      case "admin" -> administratorService.loadAdministratorByEmail(username);
      case "student" -> studentService.loadStudentByEmail(username);
      case "teacher" -> teacherService.loadTeacherByEmail(username);
      default -> throw new UsernameNotFoundException("Tipo de usuário não reconhecido.");
    };
  }
}
