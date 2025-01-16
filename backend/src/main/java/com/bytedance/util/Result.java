package com.bytedance.util;
import lombok.*;

import java.io.Serializable;

/**
 * @author: 繁星_逐梦
 * @date: 2025/1/16 下午3:20
 * @description: 统一响应封装类，包含状态码和消息
 */

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> implements Serializable {

    /**
     * 响应码
     */
    private Integer code;

    /**
     * 响应信息
     */
    private String message;

    /**
     * 响应数据
     */
    private T data;

    private boolean status;

    /**
     * 枚举：响应码和信息的集合
     */
    public enum ResultCode {
        /**
         * 成功
         */
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

        ResultCode(Integer code, String message) {
            this.code = code;
            this.message = message;
        }
    }

    private Result(ResultCode resultCode, T data, boolean status) {
        this.code = resultCode.code;
        this.message = resultCode.message;
        this.data = data;
        this.status = status;
    }

    /**
     * 无数据成功返回
     */
    public static <T> Result<T> success() {
        return new Result<>(ResultCode.SUCCESS, null, true);
    }

    /**
     * 带数据成功返回
     */
    public static <T> Result<T> success(T data) {
        return new Result<>(ResultCode.SUCCESS, data, true);
    }

    /**
     * 失败返回
     */
    public static <T> Result<T> fail() {
        return new Result<>(ResultCode.FAIL, null, false);
    }

    /**
     * 带数据的失败返回
     */
    public static <T> Result<T> fail(T data) {
        return new Result<>(ResultCode.FAIL, data, false);
    }

    @Override
    public String toString() {
        return "Result [code=" + code + ", message=" + message + ", data=" + data + "]";
    }
}

