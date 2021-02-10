package com.lzj.common.bean;

/**
 * @Description:
 * @Author: lzj
 * @Date： 2021/2/10 11:44
 */
public class ResultTool<E> {

    /**
     * 内部异常
     *
     * @return the ResultTool
     */
    public static Result error() {
        return error(ENResultCode.BUSINESS_ERROR);
    }

    public static Result error(ENResultCode resultCode ,String msg) {
        return new Result().setCode(resultCode.getCode()).setMessage(msg);
    }

    public static Result error(ENResultCode resultCode) {
        return new Result().setCode(resultCode.getCode()).setMessage(resultCode.getMsg());
    }

    /**
     *
     * @param message the message
     * @return the ResultTool
     */
    public static  Result error(String message) {
        return error(ENResultCode.BUSINESS_ERROR).setMessage(message);
    }

    /**
     * 操作成功
     *
     * @param <E> the element type
     * @return the ResultTool
     */
    public static <E> Result<E> ok() {
        return new Result<>();
    }

    /**
     * 操作成功
     *
     * @param <E> the type parameter
     * @param o the o
     * @return the ResultTool
     */
    public static <E> Result<E> ok(E o) {
        return new Result<>(o);
    }
}
