package com.booknetworkapi.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookDTO {

    Long id;
    @NotNull(message = "100")
    @NotEmpty(message = "100")
    String title;

    @NotNull(message = "100")
    @NotEmpty(message = "100")
    String authorName;

    @NotNull(message = "100")
    @NotEmpty(message = "100")
    String isbn;

    @NotNull(message = "100")
    @NotEmpty(message = "100")
    String synopsis;
    Boolean sharable;
}
