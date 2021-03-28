package com.hsbc.librarymanager.dto;

import static com.hsbc.librarymanager.util.ModelFactory.buildRentalDto;

import org.junit.jupiter.api.Test;

public class RentalDtoTest extends AbstractObjectValidationTester<RentalDto> {

  @Override
  RentalDto returnValidObject() {
    return buildRentalDto();
  }

  @Test
  public void testSucceed() {
    testSuccess(objectToBeValidated);
  }

  @Test
  public void testBookItemIdIsNull() {
    objectToBeValidated.setBookItemId(null);
    testFailure(objectToBeValidated, "must not be null", "bookItemId");
  }

  @Test
  public void testReaderIdIsNull() {
    objectToBeValidated.setReaderId(null);
    testFailure(objectToBeValidated, "must not be null", "readerId");
  }
}
