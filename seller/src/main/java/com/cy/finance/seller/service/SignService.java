package com.cy.finance.seller.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: chuan yu
 * @Date: 4/12/19 12:03 AM
 */
@Service
public class SignService {

  private static Map<String,String> PUBLIC_KEYS;

  static {
    PUBLIC_KEYS = new HashMap<>();
    PUBLIC_KEYS.put("1000", "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDovNC1bzzZ3lu//xLUIud8a8Fu\n" +
      "bvgXVxFu7Z3jbBjc3thot8gIrpZt951MkdfcUK091kHiDmwiAnMkDJvZI1Y9cWcF\n" +
      "gyKjczR1iDusUTPMGwHkligBx4ocVyoREz8mC0JliSnn8OKhutvnegyFsDI5lVuV\n" +
      "ZyFQPGbzvXtYIJ+cBwIDAQAB");
  }

  public String getPublicKey(String authId) {
    return PUBLIC_KEYS.get(authId);
  }
}
