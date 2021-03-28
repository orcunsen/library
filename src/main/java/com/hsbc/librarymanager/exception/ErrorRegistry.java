package com.hsbc.librarymanager.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum ErrorRegistry {
  INTERNAL_ERROR(1, "Internal server error.", INTERNAL_SERVER_ERROR),
  BOOK_NOT_FOUND(2, "Book not found.", NOT_FOUND),
  BOOK_ITEM_NOT_FOUND(3, "Book item not found.", NOT_FOUND),
  READER_NOT_FOUND(4, "Book item not found.", NOT_FOUND),
  RENTAL_NOT_FOUND(5, "Rental not found.", NOT_FOUND),
  MAXIMUM_RENTAL_COUNT(6, "You reached maximum rental count.", BAD_REQUEST),
  DESTROYED_BOOK_ITEM(7, "This book item has been destroyed.", BAD_REQUEST),
  RENTAL_HAS_ALREADY_RETURNED(8, "This rental has already returned.", BAD_REQUEST);

  private int code;
  private String message;
  private HttpStatus httpStatus;
}
