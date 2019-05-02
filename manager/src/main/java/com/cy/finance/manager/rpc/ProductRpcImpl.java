package com.cy.finance.manager.rpc;

import com.cy.finance.api.ProductRpc;
import com.cy.finance.api.domain.ProductRpcRequest;
import com.cy.finance.entity.Product;
import com.cy.finance.manager.service.ProductService;
import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: chuan yu
 * @Date: 4/10/19 10:18 PM
 */
@AutoJsonRpcServiceImpl
@Service
public class ProductRpcImpl implements ProductRpc {

  @Autowired
  private ProductService productService;

  @Override
  public List<Product> findProductList(ProductRpcRequest request) {
    Pageable pageable = PageRequest.of(request.getPage(), request.getPageSize(), request.getSort(), request.getOrderBy());
    return productService.listProducts(request.getIds(), request.getMinRewardRate(),
      request.getMaxRewardRate(), request.getStatuses(), pageable).getContent();
  }

  @Override
  public Product findProduct(String id) {
    return productService.findProduct(id);
  }
}
