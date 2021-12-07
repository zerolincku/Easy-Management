package com.linck.management.common.api;


/**
 * @author: linck
 * 枚举了一些常用API操作码
 */
public enum ResultCodeEnum implements IErrorCode {
    SUCCESS(200, "操作成功"),
    FAILED(500, "操作失败"),
    NO_STATE_VALUE(501, "没有对应的state"),
    NO_RESULT_VALUE(502, "没有对应的result"),
    VALIDATE_FAILED(404, "参数检验失败"),
    UNAUTHORIZED(401, "暂未登录或token已经过期"),
    FORBIDDEN(403, "没有相关权限");
    private int code;
    private String message;

    private ResultCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 通过状态码获取枚举对象
     *
     * @param code 状态码
     * @return 枚举对象
     */
    public static ResultCodeEnum getByCode(int code) {
        for (ResultCodeEnum resultEnum : ResultCodeEnum.values()) {
            if (code == resultEnum.getCode()) {
                return resultEnum;
            }
        }
        return null;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }


}
