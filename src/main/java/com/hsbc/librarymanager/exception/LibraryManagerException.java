package com.hsbc.librarymanager.exception;

import lombok.Getter;

@Getter
public class LibraryManagerException extends RuntimeException {

  private final ErrorRegistry errorRegistry;

  public LibraryManagerException(ErrorRegistry errorRegistry) {
    this.errorRegistry = errorRegistry;
  }
}
