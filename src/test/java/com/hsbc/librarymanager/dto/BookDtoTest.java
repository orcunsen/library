package com.hsbc.librarymanager.dto;

import static com.hsbc.librarymanager.util.ModelFactory.buildBookDto;

import org.junit.jupiter.api.Test;

public class BookDtoTest extends AbstractObjectValidationTester<BookDto> {

  @Override
  BookDto returnValidObject() {
    return buildBookDto();
  }

  @Test
  public void testSucceed() {
    testSuccess(objectToBeValidated);
  }

  @Test
  public void testTitleIsNull() {
    objectToBeValidated.setTitle(null);
    testFailure(objectToBeValidated, "must not be null", "title");
  }

  @Test
  public void testTitleIsBlank() {
    objectToBeValidated.setTitle("");
    testFailure(objectToBeValidated, "must not be blank", "title");
  }

  @Test
  public void testAuthorIsNull() {
    objectToBeValidated.setAuthor(null);
    testFailure(objectToBeValidated, "must not be null", "author");
  }

  @Test
  public void testAuthorIsBlank() {
    objectToBeValidated.setAuthor("");
    testFailure(objectToBeValidated, "must not be blank", "author");
  }
}
