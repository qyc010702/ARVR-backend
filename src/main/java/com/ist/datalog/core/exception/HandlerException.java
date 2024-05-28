package com.ist.datalog.core.exception;

import io.swagger.annotations.ApiModel;
import org.springframework.http.HttpStatus;

import javax.persistence.EntityNotFoundException;
import javax.validation.constraints.NotNull;

/**
 * @author : LiYiFan
 * @date : 2023/10/25 15:59
 * @desc : 错误异常处理
 */

@ApiModel(value = "业务处理异常", description = "业务逻辑中捕获的运行时异常对象，可自定义网络状态码")
public class HandlerException extends RuntimeException {

    private final @NotNull Integer code;
    private final @NotNull Exception ex;

    public Integer getCode() {
        return this.code;
    }

    public Exception getEx() {
        return this.ex;
    }

    public HandlerException(Integer code, Exception ex) {
        super(ex.getMessage());
        this.code = code;
        this.ex = ex;
    }

    public HandlerException(HttpStatus status, Exception ex) {
        super(ex.getMessage());
        this.code = status.value();
        this.ex = ex;
    }

    public static HandlerException entityNotFoundByUniqueIdException(String uniqueId){
        return new HandlerException(HttpStatus.BAD_REQUEST, new EntityNotFoundException("[uniqueId: " + uniqueId + " ] 记录未找到"));
    }

}
