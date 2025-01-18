package com.bytedance.util;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author: 繁星_逐梦
 * @date: 2025/1/16 下午3:20
 * @description: 统一响应封装类，包含状态码和消息
 */
@Setter
@Getter
@Accessors (chain = true) // 支持链式调用
@NoArgsConstructor // 无参构造器
@AllArgsConstructor // 全参构造器
@ToString // 自动生成 toString 方法
public class Result<T> implements Serializable {

  private Integer code; // 响应码

  private String message; // 响应信息

  private T data; // 响应数据

  // 枚举：响应码和信息的集合
  public enum ResultCode {

      SUCCESS(200, "成功"),
      FAIL(1000, "失败"),
      FAILED(400, "请求失败"),
      NOT_FOUND(404, "未找到"),
      SERVER_ERROR(500, "服务器内部出错"),
      PARAM_IS_INVALID(1001, "参数无效"),
      PARAM_IS_BLANK(1002, "参数为空"),
      PARAM_TYPE_ERROR(1003, "参数类型错误"),
      PARAM_NOT_COMPLETE(1004, "参数缺失"),
      USER_NOT_LOGIN_IN(2001, "用户未登录"),
      USER_LOGIN_ERROR(2002, "账号不存在或密码错误"),
      USER_ACCOUNT_FORBIDDEN(2003, "账户被禁用"),
      USER_NOT_EXISTS(2004, "用户不存在"),
      USER_HAS_EXISTED(2005, "用户已存在");

    private final Integer code;

    private final String message;

    ResultCode (Integer code, String message) {
      this.code = code;
      this.message = message;
    }

  }

  /**
   * 静态工厂方法：传入 ResultCode 枚举对象和数据
   */
  public static <T> Result<T> of (ResultCode resultCode, T data) {
    return new Result<>(resultCode.code, resultCode.message, data);
  }

  /**
   * 静态工厂方法：传入自定义 code,message,data. 范围: [3000,6000]
   */
  public static <T> Result<T> of (Integer code, String message, T data) {
    return new Result<>(code, message, data);
  }

}
