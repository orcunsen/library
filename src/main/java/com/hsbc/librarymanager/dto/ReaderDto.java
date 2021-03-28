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
public class ReaderDto implements Serializable {

  @NotNull
  @NotBlank
  private String name;

  @NotNull
  @NotBlank
  private String lastName;
}
