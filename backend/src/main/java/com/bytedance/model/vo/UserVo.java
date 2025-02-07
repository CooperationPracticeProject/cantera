package com.bytedance.model.vo;

import lombok.Data;

@Data
public class UserVo {

  private Long id;

  private String email;

  private String username;

  private String nickname;

  private String avatar;

  private Integer role;

  private String accessToken;

  private String refreshToken;
}
