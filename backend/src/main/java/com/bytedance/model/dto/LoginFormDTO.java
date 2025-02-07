package com.bytedance.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class LoginFormDTO {
  @Email
  private String email;
  @Pattern(regexp = "^\\d{6}$", message = "验证码必须是6位数字")
  private String code;
  @Pattern (
    regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,20}$",
    message = "密码至少是六位数字且至少包含一位数字或字母"
  )
  private String password;
}
