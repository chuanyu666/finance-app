package com.cy.finance.manager.repository;

import com.cy.finance.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, String>, JpaRepository<Product, String>, JpaSpecificationExecutor<Product> {


}
