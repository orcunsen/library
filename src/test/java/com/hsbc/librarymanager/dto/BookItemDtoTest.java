package com.hsbc.librarymanager.dto;

import static com.hsbc.librarymanager.util.ModelFactory.buildBookItemDto;

import org.junit.jupiter.api.Test;

public class BookItemDtoTest extends AbstractObjectValidationTester<BookItemDto> {

  @Override
  BookItemDto returnValidObject() {
    return buildBookItemDto();
  }

  @Test
  public void testSucceed() {
    testSuccess(objectToBeValidated);
  }

  @Test
  public void testBookIdIsNull() {
    objectToBeValidated.setBookId(null);
    testFailure(objectToBeValidated, "must not be null", "bookId");
  }
}
