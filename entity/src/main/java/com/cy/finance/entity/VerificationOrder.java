package com.cy.finance.entity;

import com.cy.finance.entity.enums.OrderType;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
public class VerificationOrder {

  @Id
  private String orderId;
  private String channelId;
  private String channelUserId;
  @Enumerated(EnumType.STRING)
  private OrderType orderType;
  private String productId;
  private BigDecimal amount;
  private String outerOrderId;
  private Date createAt;
}
