package com.scholario.scholario_demo.dto.subject;

import com.scholario.scholario_demo.entiity.Subject;

public record SubjectCreationDto(
    String name,
    String description
) {

    public Subject toEntity() {
        return new Subject(
            name, description
        );
    }
}
