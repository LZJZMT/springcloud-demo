package com.lzj.common.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.http.HttpStatus;

/**
 * @Description:
 * @Author: lzj
 * @Date： 2021/2/10 11:37
 */

@Getter
@Setter
@Accessors(chain = true)
public class Result<T> {

    private boolean success;

    private Integer code = HttpStatus.OK.value();

    private String message = "操作成功";

    private T result;

    public Result(T result){
        this.result = result;
    }

    public Result(){}

    public boolean isSuccess() {
        return HttpStatus.OK.value() == code;
    }
}
