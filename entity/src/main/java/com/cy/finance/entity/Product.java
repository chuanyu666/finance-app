package com.cy.finance.entity;

import com.cy.finance.entity.enums.ProductStatus;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
public class Product {
  @Id
  private String id;
  private String name;
  @Enumerated(EnumType.STRING)
  private ProductStatus status;
  /**
   * 起投金额
   */
  private BigDecimal thresholdAmount;
  /**
   * 投资步长
   */
  private BigDecimal stepAmount;
  /**
   * 锁定期
   */
  private Integer lockTerm;
  /**
   * 收益率,0-100 百分比值
   */
  private BigDecimal rewardRate;
  private String memo;
  private Date createAt;
  private Date updateAt;
  private String createUser;
  private String updateUser;
}
