package com.cy.finance.manager.controller;

import com.cy.finance.entity.Product;
import com.cy.finance.entity.enums.ProductStatus;
import com.cy.finance.manager.service.ProductService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductController {

  private static Logger LOG = LoggerFactory.getLogger(ProductController.class);

  @Autowired
  private ProductService service;

  @RequestMapping(method = RequestMethod.POST)
  public Product addProduct(@RequestBody Product product) {
    return service.addProduct(product);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public Product findProduct(@PathVariable String id) {
    return service.findProduct(id);
  }

  @RequestMapping(method = RequestMethod.GET)
  public Page<Product> listProducts(String ids, BigDecimal minRewardRate, BigDecimal maxRewardRate, String statuses,
                                    @RequestParam(defaultValue = "0") int pageNum, @RequestParam(defaultValue = "10") int pageSize) {
    List<String> idList = null;
    List<ProductStatus> statusList = null;
    if (StringUtils.isNoneBlank(ids)) {
      idList = Arrays.asList(ids.split(","));
    }
    if (StringUtils.isNoneBlank(statuses)) {
      statusList = Arrays.stream(statuses.split(",")).map(ProductStatus::valueOf).collect(Collectors.toList());
    }
    Pageable pageable = new PageRequest(pageNum, pageSize);
    return service.listProducts(idList, minRewardRate, maxRewardRate, statusList, pageable);
  }
}
