package com.hsbc.librarymanager.dto;

import java.io.Serializable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookDto implements Serializable {

  @NotNull
  @NotBlank
  private String title;

  @NotNull
  @NotBlank
  private String author;

  private int publicationYear;
}
