package com.hsbc.librarymanager.exception;

import static com.hsbc.librarymanager.exception.ErrorRegistry.INTERNAL_ERROR;
import static org.springframework.core.Ordered.HIGHEST_PRECEDENCE;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

  @ExceptionHandler(LibraryManagerException.class)
  @Order(HIGHEST_PRECEDENCE)
  @ResponseBody
  public ResponseEntity<Error> handleLibraryManagerException(LibraryManagerException exception) {
    log.error(
        "An error occurred and got an exception. Exception Type: {}, Exception: {}",
        exception.getClass(),
        exception.getMessage());
    return new ResponseEntity<>(
        createError(exception.getErrorRegistry()), exception.getErrorRegistry().getHttpStatus());
  }

  @ExceptionHandler(Exception.class)
  public final ResponseEntity<Error> handleAllExceptions(Exception exception) {
    log.error(
        "An error occurred and got an exception. Exception Type: {}, Exception: {}",
        exception.getClass(),
        exception.getMessage());
    return new ResponseEntity<>(createError(INTERNAL_ERROR), INTERNAL_ERROR.getHttpStatus());
  }

  private Error createError(ErrorRegistry errorRegistry) {
    return Error.builder()
        .code(errorRegistry.getCode())
        .message(errorRegistry.getMessage())
        .build();
  }
}
