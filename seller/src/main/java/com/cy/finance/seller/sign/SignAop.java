package com.cy.finance.seller.sign;

import com.cy.finance.seller.service.SignService;
import com.cy.finance.util.RSAUtil;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * @Author: chuan yu
 * @Date: 4/12/19 12:01 AM
 */
@Component
@Aspect
public class SignAop {

  @Autowired
  private SignService signService;

  @Before(value = "execution(* com.cy.finance.seller.controller.*.*(..)) && args(authId, sign, text, ..)")
  public void verify(String authId, String sign, SignText text) {
    String publicKey = signService.getPublicKey(authId);
    Assert.isTrue(RSAUtil.verify(text.toText(), sign, publicKey), "failed to verify");
  }
}
