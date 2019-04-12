package com.cy.finance.seller.sign;

import com.cy.finance.util.JsonUtil;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * @Author: chuan yu
 * @Date: 4/11/19 11:57 PM
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder(alphabetic = true)
public interface SignText {
  default String toText() {
    return JsonUtil.toJson(this);
  }
}
