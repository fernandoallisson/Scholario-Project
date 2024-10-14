package com.scholario.scholario_demo.dto.classes;

import com.scholario.scholario_demo.entiity.Classe;

/**
 * The type Class creation dto.
 */
public record ClassCreationDto(String name, String shift, int year) {

  /**
   * To entity classe.
   *
   * @return the classe
   */
  public Classe toEntity() {
    return new Classe(
        name, shift, year
    );
  }
}
