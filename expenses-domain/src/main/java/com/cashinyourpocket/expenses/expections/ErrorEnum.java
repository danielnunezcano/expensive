package com.cashinyourpocket.expenses.expections;

public enum ErrorEnum {

  /** The bbdd. */
  DB(1000, "There has been an error in the access to BBDD. Please retry the operation (if the problem persists, contact support)."),

  /** The bbdd sql grammar. */
  DB_BAD_SQL_GRAMMAR(1001, "The SQL Grammar is incorrect"),

  /** The bbdd. */
  DB_DATA_ACCESS_FAILURE(1002, "Data Access Failure"),

  /** The bbdd. */
  DB_ERROR_INSERT(1003,
      "Error when entering response to DB, wrong format. (if the problem persists, contact support)."),

  /** Create new analytics with existing id. */
  DB_NEW_ANALYTIC_EXISTS(1101, "Can not create a new analytic with an existing ID"),

  /** Edit analytic id. */
  DB_EDIT_ANALYTIC_ID(1102, "Can not edit the ID of an existing report"),

  /** The ws. */
  WS(2000,
      "An error has occurred in the access to the data service. Please retry the operation (if the problem persists, contact support)."),

  /** The ws validation. */
  WS_VALIDATION(2001,
      "There has been an error in the validation WS. Please retry the operation (if the problem persists, contact support)."),

  /** The ws timeout. */
  WS_TIMEOUT(2002,
      "There has been a timeout in WS. Please retry the operation (if the problem persists, contact support)."),

  /** The validation. */
  VALIDATION(3000,
      "There has been an error in the validation of the data. Please retry the operation (if the problem persists, contact support)."),

  /** The validation range date. */
  VALIDATION_RANGE_DATE(3001, "The date filter cannot be more than 31 days old"),

  /** The data. */
  DATA(4000,
      "An error occurred when loading the data. Please retry the operation (if the problem persists, contact support)."),

  /** The general. */
  DATA_IO(4001,
      "An error occurred while reading/writing data. Please retry the operation (if the problem persists, contact support)."),

  /** The general. */
  DATA_FILE_DOWNLOAD(4002,
      "An error occurred while downloading the file. Please retry the operation (if the problem persists, contact support)."),

  /** The general. */
  DATA_FILE_UPLOAD(4003,
      "An error occurred while uploading the file. Please retry the operation (if the problem persists, contact support)."),

  /** The file not exist. */
  FILE_NOT_EXIST(404,
      "The file does not exist."),

  /** The general. */
  GENERAL(5000,
      "An error occurred while processing the request. Please retry the operation (if the problem persists, contact support).");

  /** The id. */
  private final int id;

  /** The description. */
  private final String description;

  /**
   * Instantiates a new errors enum.
   *
   * @param id the id
   * @param description the description
   */
  private ErrorEnum(final int id, final String description) {
    this.id = id;
    this.description = description;
  }

  /**
   * Gets the id.
   *
   * @return the id
   */
  public int getId() {
    return this.id;
  }

  /**
   * Gets the description.
   *
   * @return the description
   */
  public String getDescription() {
    return this.description;
  }

}
