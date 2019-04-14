package com.cy.finance.seller.enums;

import lombok.Getter;

@Getter
public enum ChannelEnum {
  ABC("123", "ABC", "/Users/chuanyu/IdeaProjects/verification");
  private String chanId;
  private String chanName;

  private String ftpPath, ftpUser, ftpPwd;

  private String rootDir;

  ChannelEnum(String chanId, String chanName, String rootDir) {
    this.chanId = chanId;
    this.chanName = chanName;
    this.rootDir = rootDir;
  }

  /**
   * 根据渠道编号获取渠道配置
   *
   * @param chanId
   * @return
   */
  public static ChannelEnum getByChanId(String chanId) {
    for (ChannelEnum channelEnum : ChannelEnum.values()) {
      if (channelEnum.getChanId().equals(chanId)) {
        return channelEnum;
      }
    }
    return null;
  }
}
