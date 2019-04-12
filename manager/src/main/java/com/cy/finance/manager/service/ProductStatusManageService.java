package com.cy.finance.manager.service;

import com.cy.finance.api.event.ProductStatusEvent;
import com.cy.finance.entity.enums.ProductStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

/**
 * @Author: chuan yu
 * @Date: 4/11/19 11:04 PM
 */
@Service
public class ProductStatusManageService {

  private static final String MQ_DESTINATION = "VirtualTopic.PRODUCT_STATUS";

  @Autowired
  private JmsTemplate jmsTemplate;

  public void changeStatus(String id, ProductStatus status) {
    ProductStatusEvent event = new ProductStatusEvent(id, status);
    jmsTemplate.convertAndSend(MQ_DESTINATION, event);
  }
}
