package com.cy.finance.manager.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice(basePackages = "com.cy.finance.controller")
public class ErrorControllerAdvice {

  @ExceptionHandler(Exception.class)
  @ResponseBody
  public ResponseEntity handleException(Exception e) {
    Map<String, Object> errorAttributes = new HashMap<>();
    String errorCode = e.getMessage();
    ErrorEnum errorEnum = ErrorEnum.getByCode(errorCode);
    errorAttributes.put("message", errorEnum.getMessage());
    errorAttributes.put("code", errorEnum.getCode());
    errorAttributes.put("canRetry", errorEnum.isCanRetry());
    return new ResponseEntity(errorAttributes, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
