package com.cy.finance.manager.error;

import org.springframework.boot.autoconfigure.web.BasicErrorController;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.ErrorViewResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public class MyErrorController extends BasicErrorController {

  public MyErrorController(ErrorAttributes errorAttributes, ErrorProperties errorProperties, List<ErrorViewResolver> errorViewResolvers) {
    super(errorAttributes, errorProperties, errorViewResolvers);
  }

  @Override
  protected Map<String, Object> getErrorAttributes(HttpServletRequest request, boolean includeStackTrace) {
    Map<String, Object> errorAttributes = super.getErrorAttributes(request, includeStackTrace);
    errorAttributes.remove("timestamp");
    errorAttributes.remove("error");
    errorAttributes.remove("path");
    errorAttributes.remove("exception");
    errorAttributes.remove("status");

    String errorCode = (String) errorAttributes.get("message");
    ErrorEnum errorEnum = ErrorEnum.getByCode(errorCode);
    errorAttributes.put("message", errorEnum.getMessage());
    errorAttributes.put("code", errorEnum.getCode());
    errorAttributes.put("canRetry", errorEnum.isCanRetry());
    return errorAttributes;
  }
}
