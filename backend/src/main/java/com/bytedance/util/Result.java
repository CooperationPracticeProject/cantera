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

      // 全局通用状态码（1xxx）
      SUCCESS(1000, "成功"),
      FAIL(1001, "失败"),
      FAILED(1002, "请求失败"),
      NOT_FOUND(1003, "未找到"),

      // 参数相关状态码（2xxx）
      PARAM_IS_INVALID(2001, "参数无效"),
      PARAM_IS_BLANK(2002, "参数为空"),
      PARAM_TYPE_ERROR(2003, "参数类型错误"),
      PARAM_NOT_COMPLETE(2004, "参数缺失"),
      INVALID_PARAM(2005, "非法参数"),

      // 用户相关状态码（3xxx）
      USER_NOT_LOGIN_IN(3001, "用户未登录"),
      USER_LOGIN_ERROR(3002, "账号不存在或密码错误"),
      USER_ACCOUNT_FORBIDDEN(3003, "账户被禁用"),
      USER_NOT_EXISTS(3004, "用户不存在"),
      USER_HAS_EXISTED(3005, "用户已存在"),

      // 文件上传相关状态码（4xxx）
      FILE_UPLOAD_SUCCESS(4000, "文件上传成功"),
      FILE_UPLOAD_FAIL(4001, "文件上传失败"),
      FILE_IS_EMPTY(4002, "上传文件为空"),
      FILE_PATH_NOT_FOUND(4003, "文件路径不存在"),
      FILE_SIZE_EXCEEDED(4004, "文件大小超出限制"),
      FILE_TYPE_NOT_ALLOWED(4005, "文件类型不允许"),

      // 服务器相关状态码（5xxx）
      SERVER_ERROR(5000, "服务器内部出错");

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
