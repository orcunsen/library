package com.hsbc.librarymanager.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.hsbc.librarymanager.exception.ErrorRegistry;
import com.hsbc.librarymanager.exception.LibraryManagerException;
import org.junit.jupiter.api.function.Executable;

public final class TestHelper {

  private TestHelper() {
  }

  public static void throwsExceptionCheck(Executable executable, ErrorRegistry errorRegistry) {
    LibraryManagerException exceptionThrown =
        assertThrows(LibraryManagerException.class, executable);

    assertEquals(exceptionThrown.getErrorRegistry().getMessage(), errorRegistry.getMessage());
    assertEquals(exceptionThrown.getErrorRegistry().getCode(), errorRegistry.getCode());
  }
}
