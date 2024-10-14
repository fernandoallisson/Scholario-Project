package com.scholario.scholario_demo.dto.subject;

import com.scholario.scholario_demo.entiity.Subject;

/**
 * The type Subject creation dto.
 */
public record SubjectCreationDto(
    String name,
    String description
) {

  /**
  * To entity subject.
  *
  * @return the subject
  */
  public Subject toEntity() {
    return new Subject(
        name, description
    );
  }
}
