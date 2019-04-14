package com.cy.finance.seller.service;

import com.cy.finance.seller.repository.OrderRepository;
import com.cy.finance.seller.repositorybackup.BackupOrderRepository;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 对账测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class VerifyTest {
  @Autowired
  private VerificationOrderService verificationOrderService;

  @Autowired
  private OrderRepository orderRepository;

  @Autowired
  private BackupOrderRepository backupOrderRepository;

  @Test
  public void makeVerificationTest() {
    Date day = new GregorianCalendar(2019, 3, 11).getTime();
    File file = verificationOrderService.generateVerificationOrderFile("123", day);
    System.out.println(file.getAbsolutePath());
  }
  @Test
  public void saveVerificationOrders(){
    Date day = new GregorianCalendar(2019,3,11).getTime();
    verificationOrderService.saveChannelOrders("123",day);
  }
  @Test
  public void verifyTest(){
    Date day = new GregorianCalendar(2019,3,11).getTime();
    System.out.println(String.join(";", verificationOrderService.verifyOrder("123", day)));
  }

  @Test
  public void queryOrder(){
    System.out.println(orderRepository.findAll());
    System.out.println(backupOrderRepository.findAll());
  }
}
