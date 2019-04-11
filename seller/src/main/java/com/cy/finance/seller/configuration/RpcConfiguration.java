package com.cy.finance.seller.configuration;

import com.cy.finance.api.ProductRpc;
import com.googlecode.jsonrpc4j.spring.AutoJsonRpcClientProxyCreator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @Author: chuan yu
 * @Date: 4/10/19 10:25 PM
 */
@Configuration
@ComponentScan(basePackageClasses = {ProductRpc.class})
public class RpcConfiguration {

  private static Logger LOG = LoggerFactory.getLogger(RpcConfiguration.class);

  @Bean
  public AutoJsonRpcClientProxyCreator rpcClientProxyCreator(@Value("${rpc.manager.url}") String url) {
    AutoJsonRpcClientProxyCreator creator = new AutoJsonRpcClientProxyCreator();
    try {
      creator.setBaseUrl(new URL(url));
    } catch (MalformedURLException e) {
      LOG.error("error create rpc", e);
    }
    creator.setScanPackage(ProductRpc.class.getPackage().getName());
    return creator;
  }
}
