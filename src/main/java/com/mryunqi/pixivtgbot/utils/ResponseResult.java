package com.mryunqi.pixivtgbot.utils;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author mryunqi
 * @date 2022/12/24
 */
@Data
@Accessors(chain = true)
public class ResponseResult<T> implements Serializable {

    private static final long serialVersionUID = -1L;

    private Integer code;

    private String message;

    private T data;

    public ResponseResult(Integer code, String message, T data) {
        super();
        this.code = code;
        this.message = message;
        this.data = data;
    }

    private static <T> ResponseResult<T> build(Integer code, String message, T data) {
        return new ResponseResult<>(code, message, data);
    }

    public static <T> ResponseResult<T> ok() {
        return new ResponseResult<>(RespCode.OK.code, RespCode.OK.message, null);
    }

    public static <T> ResponseResult<T> ok(T data) {
        return build(RespCode.OK.code, RespCode.OK.message, data);
    }

    public static <T> ResponseResult<T> fail() {
        return fail(RespCode.ERROR.message);
    }

    public static <T> ResponseResult<T> fail(String message) {
        return fail(RespCode.ERROR, message);
    }

    public static <T> ResponseResult<T> fail(RespCode respCode) {
        return fail(respCode, respCode.message);
    }

    public static <T> ResponseResult<T> fail(RespCode respCode, String message) {
        return build(respCode.getCode(), message, null);
    }

    public enum RespCode {
        // 操作成功的操作
        OK(20000, "请求成功"),
        MY_ERROR(20433, "自定义异常"),
        UNAUTHORIZED(20401, "未授权"),
        LOGIN_FAIL(20402, "账号或密码错误"),
        ERROR(20400, "未知异常"),

        // 服务器内部异常操作
        SYSTEM_ERROR(20500,"系统异常，500。"),
        DATA_ERROR(4000001,"参数异常"),
        VALID_DATA_ERROR(4000002,"参数校验异常"),
        USER_ERROR(4000003,"账号异常，请重新注册。"),
        USER_LOCK_ERROR(4000004,"该账号涉嫌违规，已被封禁，用户已被强制登出。"),
        USER_PASSWORD_ERROR(4000005,"账号密码错误。"),
        TOKEN_NOT_NULL_ERROR(4010001,"Token凭证不能为空，请重新登录获取。"),
        TOKEN_LOSE_ERROR(4010001,"Token认证失败，请重新登录获取。。"),
        ACCOUNT_LOCK(4010001,"该账号被锁定,请联系系统管理员"),
        ACCOUNT_HAS_DELETED_ERROR(4010001,"该账号已被删除，请联系系统管理员"),
        TOKEN_PAST_DUE(4010002,"token失效,请刷新token"),
        NOT_PERMISSION(4030001,"没有权限访问该资源");

        RespCode(int code, String message) {
            this.code = code;
            this.message = message;
        }

        private final int code;
        private final String message;

        public int getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }
    }
}


