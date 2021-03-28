package com.hsbc.librarymanager.dto;

import com.hsbc.librarymanager.enums.BookItemStatus;
import java.io.Serializable;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookItemDto implements Serializable {

  @NotNull
  private Long bookId;

  @Valid
  @NotNull
  private BookItemStatus status;
}
