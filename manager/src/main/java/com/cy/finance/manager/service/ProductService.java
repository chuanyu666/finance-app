package com.cy.finance.manager.service;

import com.cy.finance.entity.Product;
import com.cy.finance.entity.enums.ProductStatus;
import com.cy.finance.manager.error.ErrorEnum;
import com.cy.finance.manager.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.persistence.criteria.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProductService {

  private static Logger LOG = LoggerFactory.getLogger(ProductService.class);

  @Autowired
  private ProductRepository repository;

  public Page<Product> listProducts(List<String> ids, BigDecimal minRewardRate, BigDecimal maxRewardRate, List<ProductStatus> statuses, Pageable pageable) {

    Specification<Product> specification = (Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
      Expression<String> id = root.get("id");
      Expression<BigDecimal> rewardRate = root.get("rewardRate");
      Expression<String> status = root.get("status");
      List<Predicate> predicates = new ArrayList<>();
      if (ids != null && !ids.isEmpty()) {
        predicates.add(id.in(ids));
      }
      if (minRewardRate !=null && BigDecimal.ZERO.compareTo(minRewardRate) < 0) {
        predicates.add(cb.ge(rewardRate, minRewardRate));
      }
      if (maxRewardRate != null && BigDecimal.ZERO.compareTo(maxRewardRate) < 0) {
        predicates.add(cb.le(rewardRate, maxRewardRate));
      }
      if (statuses != null && !statuses.isEmpty()) {
        predicates.add(status.in(statuses));
      }
      query.where(predicates.toArray(new Predicate[0]));
      return null;
    };

    return repository.findAll(specification, pageable);
  }

  public Product findProduct(String id) {
    Assert.notNull(id, ErrorEnum.ID_NOT_NULL.getCode());
    return repository.findOne(id);
  }

  public Product addProduct(Product product) {
    LOG.debug("create product {}", product);
    checkProduct(product);
    setDefaultForProduct(product);
    Product result = repository.save(product);
    LOG.debug("create product successfully {}", result);
    return result;
  }

  private void setDefaultForProduct(Product product) {
    if (product.getCreateAt() == null) {
      product.setCreateAt(new Date());
    }
    if (product.getUpdateAt() == null) {
      product.setUpdateAt(new Date());
    }
    if (product.getStepAmount() == null) {
      product.setStepAmount(BigDecimal.ZERO);
    }
    if (product.getLockTerm() == null) {
      product.setLockTerm(0);
    }
    if (product.getStatus() == null) {
      product.setStatus(ProductStatus.AUDITING);
    }
  }

  private void checkProduct(Product product) {
    Assert.notNull(product.getId(), "Id can't be null");
    Assert.isTrue(BigDecimal.ZERO.compareTo(product.getRewardRate()) < 0 &&
      BigDecimal.valueOf(30).compareTo(product.getRewardRate()) >= 0,
      "Reward rate should be greater than 0 and less or equal to 30");
    Assert.isTrue(BigDecimal.valueOf(product.getStepAmount().longValue()).compareTo(product.getStepAmount()) == 0,
      "Invest step need to be Integer");
  }
}
