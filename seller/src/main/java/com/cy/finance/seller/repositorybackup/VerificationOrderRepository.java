package com.cy.finance.seller.repositorybackup;

import com.cy.finance.entity.VerificationOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @Author: chuan yu
 * @Date: 4/13/19 4:44 PM
 */
@Repository
public interface VerificationOrderRepository extends JpaRepository<VerificationOrder, String>, JpaSpecificationExecutor<VerificationOrder> {

  @Query(value = "SELECT CONCAT_WS('|', order_id,outer_order_id,channel_id,channel_user_id,product_id,order_type,amount,DATE_FORMAT(create_at,'%Y-%m-%d %H:%i:%s')) FROM order_t WHERE order_status = 'SUCCESS' AND channel_id = ?1 AND create_at >= ?2 AND create_at < ?3",nativeQuery = true)
  List<String> queryVerificationOrders(String channelId, Date start, Date end);

  @Query(value = "SELECT t.`order_id` FROM order_t t LEFT JOIN verification_order v ON t.`channel_id` = ?1 AND t.`outer_order_id` = v.`order_id` WHERE v.`order_id` IS NULL AND t.create_at >= ?2 AND t.create_at < ?3",nativeQuery = true)
  List<String> queryExcessOrders(String channelId, Date start, Date end);

  @Query(value = "SELECT v.`order_id` FROM verification_order v LEFT JOIN order_t t ON t.`channel_id` = ?1 AND v.`outer_order_id` = t.`order_id` WHERE t.`order_id` IS NULL AND v.create_at >= ?2 AND v.create_at < ?3",nativeQuery = true)
  List<String> queryMissOrders(String channelId, Date start, Date end);

  @Query(value = "SELECT t.order_id FROM order_t t JOIN verification_order v ON t.`channel_id` = ?1 AND t.`outer_order_id` = v.`order_id` WHERE CONCAT_WS('|',t.channel_id,t.channel_user_id,t.product_id,t.order_type,t.amount,DATE_FORMAT( t.create_at,'%Y-%m-%d %H:%i:%s')) != CONCAT_WS('|',v.channel_id,v.channel_user_id,v.product_id,v.order_type,v.amount,DATE_FORMAT( v.create_at,'%Y-%m-%d %H:%i:%s')) AND t.create_at >= ?2 AND t.create_at < ?3",nativeQuery = true)
  List<String> queryDifferentOrders(String channelId, Date start, Date end);
}
