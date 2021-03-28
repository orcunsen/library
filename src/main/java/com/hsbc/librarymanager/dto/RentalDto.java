package com.hsbc.librarymanager.dto;

import java.io.Serializable;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RentalDto implements Serializable {

  @NotNull
  private Long bookItemId;

  @NotNull
  private Long readerId;
}
