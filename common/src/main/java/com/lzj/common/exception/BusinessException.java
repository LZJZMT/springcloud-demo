package com.lzj.common.exception;

import com.lzj.common.bean.ENResultCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 业务异常类
 *
 * @author wuligao
 * @date 2020/3/27 14:22
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BusinessException extends RuntimeException {
    
    /**
     * 状态码
     */
    private int code;
    
    /**
     * 提示信息
     */
    private String message;

    public BusinessException(){
        this.code = ENResultCode.BUSINESS_ERROR.getCode();
        this.message = ENResultCode.BUSINESS_ERROR.getMsg();
    }

    public BusinessException(String message) {
        this.code = ENResultCode.BUSINESS_ERROR.getCode();
        this.message = message;
    }

    public BusinessException(int code, String message) {
        this.code = code;
        this.message = message;
    }
    
    public BusinessException(ENResultCode codeEnum, String message) {
        this.code = codeEnum.getCode();
        this.message = message;
    }
    
    public BusinessException(ENResultCode codeEnum) {
        this.code = codeEnum.getCode();
        this.message = codeEnum.getMsg();
    }
}
