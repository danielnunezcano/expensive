package com.cashinyourpocket.expenses.expections;

public class CustomException extends RuntimeException {
  /**
   * The Constant serialVersionUID.
   */
  private static final long serialVersionUID = 1828357407161552449L;

  /**
   * The code.
   */
  private final int code;

  private final String message;

  public CustomException(final ErrorEnum error) {
    this.code = error.getId();
    this.message = error.getDescription();
  }
}
