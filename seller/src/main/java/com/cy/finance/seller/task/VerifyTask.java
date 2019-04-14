package com.cy.finance.seller.task;

import com.cy.finance.seller.enums.ChannelEnum;
import com.cy.finance.seller.service.VerificationOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class VerifyTask {

  @Autowired
  private VerificationOrderService verificationOrderService;

  //    @Scheduled(cron = "0/5 * * * * ? ")
  public void hello() {
    System.out.println("hello");
  }

  @Scheduled(cron = "0 0 1,3,5 * * ? ")
  public void makeVerificationFile() {
    Date yesterday = new Date(System.currentTimeMillis() - 24 * 60 * 60 * 1000);
    for (ChannelEnum chanEnum : ChannelEnum.values()) {
      verificationOrderService.generateVerificationOrderFile(chanEnum.getChanId(), yesterday);
    }
  }

  @Scheduled(cron = "0 0 2,4,6 * * ? ")
  public void verify() {
    Date yesterday = new Date(System.currentTimeMillis() - 24 * 60 * 60 * 1000);
    for (ChannelEnum chanEnum : ChannelEnum.values()) {
      verificationOrderService.verifyOrder(chanEnum.getChanId(), yesterday);
    }
  }
}
