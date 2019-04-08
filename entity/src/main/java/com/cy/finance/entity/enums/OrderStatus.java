package com.cy.finance.entity.enums;

import lombok.Getter;

/**
 * Order status
 */
public enum OrderStatus {
  INIT("INIT"),
  PROCESS("PROCESS"),
  SUCCESS("SUCCESS"),
  FAIL("FAIL");

  @Getter
  private String desc;

  OrderStatus(String desc) {
    this.desc = desc;
  }
}
