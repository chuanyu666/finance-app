package com.cy.finance.swagger;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "swagger")
public class SwaggerInfo {
  private String groupName = "controller";

  private String basePackage;

  private String antPath;

  private String title = "HTTP API";

  private String description = "Swagger doc";

  private String license = "Apache License Version 2.0";
}
