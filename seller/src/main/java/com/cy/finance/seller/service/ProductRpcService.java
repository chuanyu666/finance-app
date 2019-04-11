package com.cy.finance.seller.service;

import com.cy.finance.api.ProductRpc;
import com.cy.finance.api.domain.ProductRpcRequest;
import com.cy.finance.entity.Product;
import com.cy.finance.entity.enums.ProductStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: chuan yu
 * @Date: 4/10/19 10:23 PM
 */
@Service
public class ProductRpcService {

  @Autowired
  private ProductRpc productRpc;

  public List<Product> findAllProduct() {
    ProductRpcRequest request = new ProductRpcRequest();
    List<ProductStatus> statuses = new ArrayList<>();
    statuses.add(ProductStatus.ON_SELL);
    request.setStatuses(statuses);
    request.setPage(0);
    request.setPageSize(1000);
    request.setSort(Sort.Direction.DESC);
    request.setOrderBy("rewardRate");
    return productRpc.findProductList(request);
  }

  @PostConstruct
  public void test(){
    System.out.println(findAllProduct());
  }
}
