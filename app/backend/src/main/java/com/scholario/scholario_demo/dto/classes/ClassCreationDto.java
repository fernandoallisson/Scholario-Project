package com.scholario.scholario_demo.dto.classes;

import com.scholario.scholario_demo.entiity.Classe;

public record ClassCreationDto(String name, int year) {

  public Classe toEntity() {
    return new Classe(
        name, year
    );
  }
}
