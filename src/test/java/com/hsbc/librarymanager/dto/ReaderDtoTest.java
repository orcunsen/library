package com.hsbc.librarymanager.dto;

import static com.hsbc.librarymanager.util.ModelFactory.buildReaderDto;

import org.junit.jupiter.api.Test;

public class ReaderDtoTest extends AbstractObjectValidationTester<ReaderDto> {

  @Override
  ReaderDto returnValidObject() {
    return buildReaderDto();
  }

  @Test
  public void testSucceed() {
    testSuccess(objectToBeValidated);
  }

  @Test
  public void testNameIsNull() {
    objectToBeValidated.setName(null);
    testFailure(objectToBeValidated, "must not be null", "name");
  }

  @Test
  public void testNameIsBlank() {
    objectToBeValidated.setName("");
    testFailure(objectToBeValidated, "must not be blank", "name");
  }

  @Test
  public void testLastNameIsNull() {
    objectToBeValidated.setLastName(null);
    testFailure(objectToBeValidated, "must not be null", "lastName");
  }

  @Test
  public void testLastNameIsBlank() {
    objectToBeValidated.setLastName("");
    testFailure(objectToBeValidated, "must not be blank", "lastName");
  }
}
