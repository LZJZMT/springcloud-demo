package com.lzj.common.exception;

import com.lzj.common.bean.ENResultCode;
import com.lzj.common.bean.Result;
import com.lzj.common.bean.ResultTool;
import com.netflix.hystrix.exception.HystrixRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import javax.validation.ConstraintViolationException;


/**
 * 全局异常处理
 *
 * @author WuliBao
 * @date 2019/3/18 10:54
 */
@Slf4j
@RestControllerAdvice
public class ExceptionHandling {
    /**
     * 非法参数异常（拦截springframework.validation.BindException）
     *
     * @param e BindException
     * @return Result<Object>
     */
    @ExceptionHandler(BindException.class)
    public Result<Object> exceptionHandler(BindException e) {
        FieldError fieldError = e.getBindingResult().getFieldError();
        if (fieldError != null) {
            String msg = ENResultCode.BAD_REQUEST.getMsg() + ":'" + fieldError.getField() + "'" + fieldError.getDefaultMessage();
            log.error(msg);
            return ResultTool.error(ENResultCode.BAD_REQUEST,msg);
        }
        else {
            log.error("error", e);
            return ResultTool.error(ENResultCode.BAD_REQUEST);
        }
    }
    
    /**
     * 非法参数异常（拦截springframework.web.bind.MethodArgumentNotValidException）
     *
     * @param e MethodArgumentNotValidException
     * @return Result<Object>
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result exceptionHandler(MethodArgumentNotValidException e) {
        FieldError fieldError = e.getBindingResult().getFieldError();
        if (fieldError == null) {
            log.error("error", e);
            return ResultTool.error(ENResultCode.BAD_REQUEST);
        }
        else {
            String msg = ENResultCode.BAD_REQUEST.getMsg() + ":'" + fieldError.getField() + "'" + fieldError.getDefaultMessage();
            log.error(msg);
            return ResultTool.error(ENResultCode.BAD_REQUEST,msg);
        }
    }
    
    /**
     * 非法参数异常
     * 
     * 处理请求参数格式错误 @RequestParam上validate失败后抛出的异常是javax.validation.ConstraintViolationException
     * 
     * @param e ConstraintViolationException
     * @return Result<Object>
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    public Result<Object> exceptionHandler(ConstraintViolationException e) {
        log.error("error:", e);
        return ResultTool.error(ENResultCode.BAD_REQUEST, e.getMessage());
    }
    
    /**
     * 对于Assert.class校验产生的异常拦截
     *
     * @param e MethodArgumentNotValidException
     * @return Result<Object>
     */
    @ExceptionHandler(value = IllegalArgumentException.class)
    public Result<Object> exceptionHandler(IllegalArgumentException e) {
        log.error("error:", e);
        return ResultTool.error(ENResultCode.BAD_REQUEST, e.getMessage());
    }
    
    /**
     * 接口熔断异常
     *
     * @param e 异常
     * @return Result
     */
    @ExceptionHandler(value = HystrixRuntimeException.class)
    public Result<Object> exceptionHandler(HystrixRuntimeException e) {
        log.error("error:", e);
        return ResultTool.error(ENResultCode.INTERFACE_FUSE);
    }
    
    /**
     * 权限不足异常处理
     *
     * @param e e
     * @return Result
     */
    @ExceptionHandler(value = AccessDeniedException.class)
    public Result<Object> exceptionHandler(AccessDeniedException e) {
        log.error("error:", e);
        return ResultTool.error(ENResultCode.FORBIDDEN);
    }
    
    /**
     * 业务异常处理
     *
     * @param e e
     * @return Result
     */
    @ExceptionHandler(value = BusinessException.class)
    public Result<Object> exceptionHandler(BusinessException e) {
        log.error("error:", e);
        return ResultTool.error(ENResultCode.BUSINESS_ERROR, e.getMessage());
    }
}
