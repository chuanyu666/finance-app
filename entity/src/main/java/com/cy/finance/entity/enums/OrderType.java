package com.cy.finance.entity.enums;

import lombok.Getter;

public enum OrderType {
  APPLY("APPLY"),
  REDEEM("REDEEM");

  @Getter
  private String desc;

  OrderType(String desc) {
    this.desc = desc;
  }
}
