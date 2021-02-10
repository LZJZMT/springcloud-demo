package com.lzj.common.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author lzj
 */

@Getter
@AllArgsConstructor
public enum ENResultCode {

    OK(200, "操作成功"),
    BAD_REQUEST(400, "非法参数"),
    UNAUTHORIZED(401, "认证失败"),
    FORBIDDEN(403, "禁止访问"),
    NOT_FOUND(404, "资源不存在"),
    INTERNAL_SERVER_ERROR(500, "内部异常"),
    INTERFACE_FUSE(510, "接口无法访问"),
    BUSINESS_ERROR(511, "操作失败"),
    ;

    private final int code;

    private final String msg;

    public ENResultCode resolve(int statusCode) {
        for (ENResultCode status : values()) {
            if (status.code == statusCode) {
                return status;
            }
        }
        return null;
    }
}
