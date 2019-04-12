package com.cy.finance.seller.controller;

import com.cy.finance.entity.Order;
import com.cy.finance.seller.params.OrderParam;
import com.cy.finance.seller.service.OrderService;
import org.aspectj.weaver.ast.Or;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/order")
public class OrderController {
  private static Logger LOG = LoggerFactory.getLogger(OrderController.class);

  @Autowired
  private OrderService orderService;

  @RequestMapping(value = "/apply", method = RequestMethod.POST)
  public Order apply(@RequestHeader String authId, @RequestHeader String sign, @RequestBody OrderParam orderParam) {
    Order order = new Order();
    BeanUtils.copyProperties(orderParam, order);
    order = orderService.applyOrder(order);
    LOG.info("apply order success:{}", order);
    return order;
  }
}
