package com.cy.finance.api.domain;

import com.cy.finance.entity.enums.ProductStatus;
import lombok.Data;
import org.springframework.data.domain.Sort;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author: chuan yu
 * @Date: 4/10/19 9:49 PM
 */
@Data
public class ProductRpcRequest {
  private List<String> ids;
  private BigDecimal minRewardRate;
  private BigDecimal maxRewardRate;
  private List<ProductStatus> statuses;
  private int page;
  private int pageSize;
  private Sort.Direction sort;
  private String orderBy;
}
