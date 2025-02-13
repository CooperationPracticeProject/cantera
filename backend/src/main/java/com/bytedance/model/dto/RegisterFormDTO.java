package com.bytedance.model.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterFormDTO {
    @Email
    private String email;
    @Pattern(regexp = "^\\d{6}$", message = "验证码必须是6位数字")
    private String code;
    @Pattern (
            regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,20}$",
            message = "密码至少是六位数字且至少包含一位数字或字母"
    )
    private String password;

    @Pattern(regexp = "^1([38][0-9]|4[579]|5[0-3,5-9]|6[6]|7[0135678]|9[89])\\d{8}$", message = "手机号格式不正确")
    private String phone;

    @Size(min = 3, max = 30, message = "昵称长度必须在3-30之间")
    private String nickname;

    @Pattern(regexp = "^[1-4]$", message = "角色ID必须是1到4之间的数字")
    private Long roleId;

}
