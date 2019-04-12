package com.cy.finance.api.event;

import com.cy.finance.entity.enums.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author: chuan yu
 * @Date: 4/11/19 11:03 PM
 */
@Data
@AllArgsConstructor
public class ProductStatusEvent {
  private String id;
  private ProductStatus status;
}
