package com.cy.finance.api;

import com.cy.finance.api.domain.ProductRpcRequest;
import com.cy.finance.entity.Product;
import com.googlecode.jsonrpc4j.JsonRpcService;

import java.util.List;

/**
 * @Author: chuan yu
 * @Date: 4/10/19 9:41 PM
 */
@JsonRpcService("rpc/products")
public interface ProductRpc {

  List<Product> findProductList(ProductRpcRequest request);

  Product findProduct(String id);
}
