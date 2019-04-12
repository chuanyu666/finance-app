package com.cy.finance.seller.params;

import com.cy.finance.entity.enums.OrderStatus;
import com.cy.finance.entity.enums.OrderType;
import com.cy.finance.seller.sign.SignText;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author: chuan yu
 * @Date: 4/11/19 11:59 PM
 */
@Data
public class OrderParam implements SignText {
  private String channelId;
  private String channelUserId;
  private String productId;
  private BigDecimal amount;
  private String outerOrderId;
  private String memo;
  private Date createAt;
}
