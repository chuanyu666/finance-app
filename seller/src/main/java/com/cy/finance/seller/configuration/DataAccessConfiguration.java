package com.cy.finance.seller.configuration;

import com.cy.finance.entity.Order;
import com.cy.finance.seller.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.LinkedHashMap;
import java.util.Map;


@Configuration
public class DataAccessConfiguration {

  @Autowired
  private JpaProperties properties;

  @Bean
  @Primary
  @ConfigurationProperties("spring.datasource.primary")
  public DataSource primaryDataSource() {
    return DataSourceBuilder.create().build();
  }

  @Bean
  @ConfigurationProperties("spring.datasource.backup")
  public DataSource backupDataSource() {
    return DataSourceBuilder.create().build();
  }

  @Bean
  @Primary
  public LocalContainerEntityManagerFactoryBean primaryEntityManagerFactory(
    EntityManagerFactoryBuilder builder, @Qualifier("primaryDataSource") DataSource dataSource) {
    return builder
      .dataSource(dataSource)
      .packages(Order.class)
      .properties(getVendorProperties(dataSource))
      .persistenceUnit("primary")
      .build();
  }

  @Bean
  public LocalContainerEntityManagerFactoryBean backupEntityManagerFactory(
    EntityManagerFactoryBuilder builder, @Qualifier("backupDataSource") DataSource dataSource) {
    return builder
      .dataSource(dataSource)
      .packages(Order.class)
      .properties(getVendorProperties(dataSource))
      .persistenceUnit("backup")
      .build();
  }

  @Bean
  @Primary
  public PlatformTransactionManager primaryTransactionManager(@Qualifier("primaryEntityManagerFactory") LocalContainerEntityManagerFactoryBean primaryEntityManagerFactory) {
    JpaTransactionManager transactionManager = new JpaTransactionManager(primaryEntityManagerFactory.getObject());
    return transactionManager;
  }

  @Bean
  public PlatformTransactionManager backupTransactionManager(@Qualifier("backupEntityManagerFactory") LocalContainerEntityManagerFactoryBean backupEntityManagerFactory) {
    JpaTransactionManager transactionManager = new JpaTransactionManager(backupEntityManagerFactory.getObject());
    return transactionManager;
  }

  // repository 扫描的时候，并不确定哪个先扫描，查看源代码
  @EnableJpaRepositories(basePackageClasses = OrderRepository.class,
    entityManagerFactoryRef = "primaryEntityManagerFactory", transactionManagerRef = "primaryTransactionManager")
  @Primary
  public class PrimaryConfiguration {
  }

  @EnableJpaRepositories(basePackageClasses = com.cy.finance.seller.repositorybackup.BackupOrderRepository.class,
    entityManagerFactoryRef = "backupEntityManagerFactory", transactionManagerRef = "backupTransactionManager")
  public class BackupConfiguration {
  }

  private Map<String, Object> getVendorProperties(DataSource dataSource) {
    Map<String, Object> vendorProperties = new LinkedHashMap<>();
    vendorProperties.putAll(properties.getHibernateProperties(dataSource));
    return vendorProperties;
  }
}
