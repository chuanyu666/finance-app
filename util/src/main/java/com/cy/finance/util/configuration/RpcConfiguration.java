package com.cy.finance.util.configuration;

import com.googlecode.jsonrpc4j.spring.AutoJsonRpcClientProxyCreator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @Author: chuan yu
 * @Date: 4/10/19 10:25 PM
 */
@Configuration
public class RpcConfiguration {

  private static Logger LOG = LoggerFactory.getLogger(RpcConfiguration.class);

  @Bean
  @ConditionalOnProperty(value = {"rpc.client.url","rpc.client.basePackage"})
  public AutoJsonRpcClientProxyCreator rpcClientProxyCreator(@Value("${rpc.client.url}") String url, @Value("${rpc.client.basePackage}") String basePackage) {
    AutoJsonRpcClientProxyCreator creator = new AutoJsonRpcClientProxyCreator();
    try {
      creator.setBaseUrl(new URL(url));
    } catch (MalformedURLException e) {
      LOG.error("error create rpc", e);
    }
    creator.setScanPackage(basePackage);
    return creator;
  }
}
