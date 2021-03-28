package com.hsbc.librarymanager.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public abstract class AbstractObjectValidationTester<O> {

  private static ValidatorFactory validatorFactory;
  private static Validator validator;
  protected O objectToBeValidated;

  @BeforeAll
  public static void createValidator() {
    validatorFactory = Validation.buildDefaultValidatorFactory();
    validator = validatorFactory.getValidator();
  }

  @AfterAll
  public static void close() {
    validatorFactory.close();
  }

  abstract O returnValidObject();

  @BeforeEach
  protected void setValidObject() {
    objectToBeValidated = returnValidObject();
  }

  public void testSuccess(O dto) {
    Set<ConstraintViolation<O>> violations = validator.validate(dto);
    assertTrue(violations.isEmpty());
  }

  public void testFailure(O dto, String expectedMessage, String propertyName) {
    Set<ConstraintViolation<O>> violations = validator.validate(dto);
    assertNotEquals(0, violations.size(), "Object Is Valid. No error found.");

    boolean violationMsgMatched = false;
    String matchedField = "";

    for (ConstraintViolation<O> violation : violations) {
      if (violation.getMessage().equals(expectedMessage)) {
        violationMsgMatched = true;
        matchedField = violation.getPropertyPath().toString();
        if (violation.getPropertyPath().toString().equals(propertyName)) {
          break;
        }
      }
    }
    assertTrue(violationMsgMatched, violationMsgMatched ? "" : formatViolationString(violations));
    assertEquals(propertyName, matchedField, "Violation found but field name is different");
  }

  private String formatViolationString(Set<ConstraintViolation<O>> violations) {
    StringBuilder builder = new StringBuilder();
    builder.append("Violation did not matched. \n Violations Received \n");
    for (ConstraintViolation<O> violation : violations) {
      builder
          .append("Violation Message :")
          .append(violation.getMessage())
          .append("\t Violation Field :")
          .append(violation.getPropertyPath().toString())
          .append("\n");
    }
    return builder.toString();
  }
}
