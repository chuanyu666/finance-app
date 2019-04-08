package com.cy.finance.entity;

import com.cy.finance.entity.enums.OrderStatus;
import com.cy.finance.entity.enums.OrderType;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
public class Order {

  @Id
  private String orderId;
  private String channelId;
  private String channelUserId;
  private OrderType orderType;
  private String productId;
  private BigDecimal amount;
  private String outOrderId;
  private OrderStatus orderStatus;
  private String memo;
  private Date createAt;
  private Date updateAt;
}
