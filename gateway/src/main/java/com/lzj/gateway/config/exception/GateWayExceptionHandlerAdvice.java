package com.lzj.gateway.config.exception;

import com.lzj.common.bean.ENResultCode;
import com.lzj.common.bean.Result;
import com.lzj.common.bean.ResultTool;
import com.lzj.common.exception.BusinessException;
import io.netty.channel.ConnectTimeoutException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.security.SignatureException;

/**
 * @Description:
 * @Author: lzj
 * @Date： 2021/2/10 15:11
 */

@Slf4j
@Component
public class GateWayExceptionHandlerAdvice {

    public Result<?> handle(ResponseStatusException ex) {
        log.error("response status exception:{}", ex);
        return ResultTool.error(ENResultCode.INTERFACE_FUSE);
    }

    public Result<?> handle(ConnectTimeoutException ex) {
        log.error("connect timeout exception:{}", ex);
        return ResultTool.error(ENResultCode.INTERFACE_FUSE);
    }

    public Result<?> handle(NotFoundException ex) {
        log.error("not found exception:{}", ex);
        return ResultTool.error(ENResultCode.NOT_FOUND);
    }

    public Result<?> handle(SignatureException ex) {
        log.error("SignatureException:{}", ex);
        return ResultTool.error(ENResultCode.UNAUTHORIZED);
    }

    public Result<?> handle(BusinessException ex) {
        log.error("BusinessException exception:{}",ex);
        return ResultTool.error(ENResultCode.BUSINESS_ERROR);
    }

    public Result<?> handle(RuntimeException ex) {
        log.error("runtime exception:{}", ex);
        return ResultTool.error(ENResultCode.BUSINESS_ERROR);
    }

    public Result<?> handle(Exception ex) {
        log.error("exception:{}", ex);
        return ResultTool.error(ENResultCode.INTERNAL_SERVER_ERROR);
    }

    public Result<?> handle(Throwable throwable) {
        Result<?> result = ResultTool.error(ENResultCode.BUSINESS_ERROR);
        if (throwable instanceof ResponseStatusException) {
            result = handle((ResponseStatusException) throwable);
        } else if (throwable instanceof ConnectTimeoutException) {
            result = handle((ConnectTimeoutException) throwable);
        } else if (throwable instanceof NotFoundException) {
            result = handle((NotFoundException) throwable);
        } else if (throwable instanceof BusinessException) {
            result = handle((BusinessException) throwable);
        } else if (throwable instanceof RuntimeException) {
            result = handle((RuntimeException) throwable);
        } else if (throwable instanceof Exception) {
            result = handle((Exception) throwable);
        }
        return result;
    }
}
