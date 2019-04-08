package com.cy.finance.swagger;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.ApiSelectorBuilder;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@ComponentScan(basePackages = "com.cy.finance.swagger")
@EnableSwagger2
public class SwaggerConfiguration {

  @Autowired
  private SwaggerInfo swaggerInfo;

  @Bean
  public Docket controllerApi() {
    Docket docket = new Docket(DocumentationType.SWAGGER_2)
      .groupName(swaggerInfo.getGroupName())
      .apiInfo(apiInfo());
    ApiSelectorBuilder builder = docket.select();
    if (StringUtils.isNoneBlank(swaggerInfo.getBasePackage())) {
      builder.apis(RequestHandlerSelectors.basePackage(swaggerInfo.getBasePackage()));
    }
    if (StringUtils.isNoneBlank(swaggerInfo.getAntPath())) {
      builder.paths(PathSelectors.ant(swaggerInfo.getAntPath()));
    }
    return builder.build();
  }


  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
      .title(swaggerInfo.getTitle())
      .description(swaggerInfo.getDescription())
      .termsOfServiceUrl("http://springfox.io")
      .contact(new Contact("Chuan", "", "chuan@test.com"))
      .license(swaggerInfo.getLicense())
      .licenseUrl("https://github.com/springfox/springfox/blob/master/LICENSE")
      .version("2.0")
      .build();
  }
}
