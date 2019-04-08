package com.cy.finance.manager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.ApiSelectorBuilder;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

  @Bean
  public Docket controllerApi() {
    Docket docket = new Docket(DocumentationType.SWAGGER_2)
      .groupName("controller")
      .apiInfo(apiInfo());
    ApiSelectorBuilder builder = docket.select();
    return builder.build();
  }


  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
      .title("HTTP API")
      .description("Manager End")
      .termsOfServiceUrl("http://springfox.io")
      .contact("chuan yu")
      .license("License Version 2.0")
      .licenseUrl("https://github.com/springfox/springfox/blob/master/LICENSE")
      .version("2.0")
      .build();
  }
}
