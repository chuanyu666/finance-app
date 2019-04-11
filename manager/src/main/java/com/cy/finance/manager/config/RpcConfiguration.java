package com.cy.finance.manager.config;

import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImplExporter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: chuan yu
 * @Date: 4/10/19 10:20 PM
 */
@Configuration
public class RpcConfiguration {

  @Bean
  public AutoJsonRpcServiceImplExporter rpcServiceImplExporter() {
    return new AutoJsonRpcServiceImplExporter();
  }
}
