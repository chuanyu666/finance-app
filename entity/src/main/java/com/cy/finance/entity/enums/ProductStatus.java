package com.cy.finance.entity.enums;

import lombok.Getter;

/**
 * Product status
 */
public enum ProductStatus {

  AUDITING("AUDITING"),

  ON_SELL("ON_SELL"),

  LOCKED("LOCKED"),

  FINISHED("FINISHED");

  @Getter
  private String desc;

  ProductStatus(String desc) {
    this.desc = desc;
  }
}
