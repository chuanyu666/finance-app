package com.cy.finance.seller.repositorybackup;

import com.cy.finance.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @Author: chuan yu
 * @Date: 4/11/19 11:25 PM
 */
@Repository
public interface BackupOrderRepository extends com.cy.finance.seller.repository.OrderRepository {
}
