package com.scholario.scholario_demo.dto.classes;

import com.scholario.scholario_demo.entiity.Classe;


public record ClassDto(Long id, String name, int year) {

  public static ClassDto fromEntity(Classe classes) {
    if (classes == null) {
      throw new IllegalArgumentException("Classe must not be null");
    }

    return new ClassDto(
        classes.getId(),
        classes.getName(),
        classes.getYear()
    );
  }
}
