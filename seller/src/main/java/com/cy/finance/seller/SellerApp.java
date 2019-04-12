package com.cy.finance.seller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @Author: chuan yu
 * @Date: 4/10/19 10:22 PM
 */
@SpringBootApplication
@EnableCaching
@EntityScan("com.cy.finance.entity")
public class SellerApp {

  public static void main(String[] args) {
    SpringApplication.run(SellerApp.class, args);
  }
}
