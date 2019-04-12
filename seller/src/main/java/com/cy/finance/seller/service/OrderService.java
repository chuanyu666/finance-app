package com.cy.finance.seller.service;

import com.cy.finance.entity.Order;
import com.cy.finance.entity.Product;
import com.cy.finance.entity.enums.OrderStatus;
import com.cy.finance.entity.enums.OrderType;
import com.cy.finance.seller.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.UUID;

/**
 * @Author: chuan yu
 * @Date: 4/11/19 11:26 PM
 */
@Service
public class OrderService {

  @Autowired
  private OrderRepository orderRepository;
  @Autowired
  private ProductRpcService productRpcService;

  public Order applyOrder(Order order) {
    checkOrder(order);
    completeOrder(order);
    order = orderRepository.saveAndFlush(order);
    return order;
  }

  private void completeOrder(Order order) {
    order.setOrderId(UUID.randomUUID().toString().replaceAll("-", ""));
    order.setOrderType(OrderType.APPLY);
    order.setOrderStatus(OrderStatus.SUCCESS);
    order.setUpdateAt(new Date());
  }

  private void checkOrder(Order order) {
    Assert.notNull(order.getOuterOrderId(), "需要外部订单编号");
    Assert.notNull(order.getChannelId(), "需要渠道编号");
    Assert.notNull(order.getChannelUserId(), "需要用户编号");
    Assert.notNull(order.getProductId(), "需要产品编号");
    Assert.notNull(order.getAmount(), "需要购买金额");
    Assert.notNull(order.getCreateAt(), "需要订单时间");

    Product product = productRpcService.findProduct(order.getProductId());
    Assert.notNull(product, "product not found");
  }
}
