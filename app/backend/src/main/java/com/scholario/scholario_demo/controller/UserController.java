package com.scholario.scholario_demo.controller;

import com.scholario.scholario_demo.dto.user.UserCreationDto;
import com.scholario.scholario_demo.dto.user.UserDto;

import com.scholario.scholario_demo.entiity.User;
import com.scholario.scholario_demo.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

  private final UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserDto> getUserById(@PathVariable Long id) throws RuntimeException {
    return ResponseEntity.ok(
        UserDto.fromEntity(userService.findUserById(id))
    );
  }

  @GetMapping
  public ResponseEntity<List<UserDto>> getAllUsers() {
    List<User> users = userService.findAllUser();

    return ResponseEntity.ok(
        users.stream()
            .map(UserDto::fromEntity)
            .toList()
    );
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<UserDto> createUser(@RequestBody UserCreationDto userCreationDto) {
    return ResponseEntity.ok(
        UserDto.fromEntity(
            userService.createUser(userCreationDto.toEntity())
        )
    );
  }

  @PutMapping("/{id}")
  public ResponseEntity<UserDto> updateUser (
      @PathVariable Long id,
      @RequestBody UserCreationDto userCreationDto) {

    return ResponseEntity.ok(
        UserDto.fromEntity(
            userService.updateUser(id, userCreationDto.toEntity())
        ));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteUserById(@PathVariable Long id) {
    userService.deleteUserById(id);
   
    return ResponseEntity.ok("Usuário de ID: " + id + " foi removido da base de dados");
  }
}
