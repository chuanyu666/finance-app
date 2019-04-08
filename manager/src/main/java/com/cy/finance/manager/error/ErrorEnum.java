package com.cy.finance.manager.error;

import lombok.Getter;

@Getter
public enum ErrorEnum {
  ID_NOT_NULL("F001", "ID CANNOT BE NULL", false),
  //...
  UNKNOWN("999", "UNKNOWN", false);

  private String code;
  private String message;
  private boolean canRetry;

  ErrorEnum(String code, String message, boolean canRetry) {
    this.code = code;
    this.message = message;
    this.canRetry = canRetry;
  }

  public static ErrorEnum getByCode(String code) {
    for (ErrorEnum errorEnum : ErrorEnum.values()) {
      if (errorEnum.code.equals(code)) {
        return errorEnum;
      }
    }
    return UNKNOWN;
  }
}
