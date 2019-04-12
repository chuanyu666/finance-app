package com.cy.finance.seller.service;

import com.cy.finance.api.ProductRpc;
import com.cy.finance.api.domain.ProductRpcRequest;
import com.cy.finance.entity.Product;
import com.cy.finance.entity.enums.ProductStatus;
import com.hazelcast.core.HazelcastInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: chuan yu
 * @Date: 4/11/19 10:35 PM
 */
@Service
public class ProductCacheService {
  private static Logger LOG = LoggerFactory.getLogger(ProductCacheService.class);
  private static final String CACHE_NAME = "finance_product";

  @Autowired
  private ProductRpc productRpc;

  @Autowired
  private HazelcastInstance hazelcastInstance;

  @Cacheable(cacheNames = CACHE_NAME)
  public Product readProductFromCache(String id) {
    LOG.info("find product by id {}", id);
    return productRpc.findProduct(id);
  }

  @CachePut(cacheNames = CACHE_NAME, key = "#product.id")
  public Product putProductToCache(Product product) {
    return product;
  }

  @CacheEvict(cacheNames = CACHE_NAME)
  public void removeProductFromCache(String id) {

  }

  public List<Product> readAllProductFromCache() {
    Map map = hazelcastInstance.getMap(CACHE_NAME);
    List<Product> products = null;
    if (!map.isEmpty()) {
      products = (List<Product>) map.values();
    } else {
      products = findAllProduct();
    }
    return products;
  }

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
}
