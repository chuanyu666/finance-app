package com.cy.finance.seller.service;

import com.cy.finance.api.event.ProductStatusEvent;
import com.cy.finance.entity.Product;
import com.cy.finance.entity.enums.ProductStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: chuan yu
 * @Date: 4/10/19 10:23 PM
 */
@Service
public class ProductRpcService implements ApplicationListener<ContextRefreshedEvent> {

  private static Logger LOG = LoggerFactory.getLogger(ProductRpcService.class);
  private static final String MQ_DESTINATION = "Consumer.cache.VirtualTopic.PRODUCT_STATUS";

  @Autowired
  private ProductCacheService productCacheService;

  public List<Product> findAllProduct() {
    return productCacheService.readAllProductFromCache();
  }

  public Product findProduct(String id) {
    Product product = productCacheService.readProductFromCache(id);
    if (product == null) {
      productCacheService.readProductFromCache(id);
    }
    return product;
  }

  //@JmsListener(destination = MQ_DESTINATION)
  private void updateProductCache(ProductStatusEvent event) {
    productCacheService.removeProductFromCache(event.getId());
    if (ProductStatus.ON_SELL.equals(event.getStatus())) {
      productCacheService.readProductFromCache(event.getId());
    }
  }

  @Override
  public void onApplicationEvent(ContextRefreshedEvent event) {
    List<Product> products = findAllProduct();
    products.forEach(product -> productCacheService.putProductToCache(product));
  }
}
