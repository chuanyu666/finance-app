package com.cy.finance.seller.controller;

import com.cy.finance.entity.Product;
import com.cy.finance.seller.service.ProductRpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: chuan yu
 * @Date: 4/11/19 10:26 PM
 */
@RestController
@RequestMapping("/products")
public class ProductController {

  @Autowired
  private ProductRpcService productRpcService;

  @GetMapping("/{id}")
  public Product findProduct(@PathVariable String id) {
    return productRpcService.findProduct(id);
  }
}
