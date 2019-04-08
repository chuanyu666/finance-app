package com.cy.finance.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan(basePackages = "com.cy.finance.entity")
@SpringBootApplication
public class ManagerApp {

  public static void main(String[] args) {
    SpringApplication.run(ManagerApp.class, args);
  }
}
