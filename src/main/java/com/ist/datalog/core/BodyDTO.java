package com.ist.datalog.core;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.http.HttpStatus;

/**
 * @author : LiYiFan
 * @date : 2023/10/25 16:06
 * @desc : 简单数据结构响应体
 */

@ApiModel(value = "简单数据结构", description = "单项数据的信息返回结构")
public class BodyDTO<T> extends HttpDTO {

    @ApiModelProperty("对象数据")
    private final T data;

    public T getData() {
        return this.data;
    }

    public static <T> BodyDTO success(T data) {
        return (new BodyBuilder(data)).build();
    }

    public static <T> BodyDTO of(HttpStatus status, T data) {
        return (new BodyBuilder(status, data)).build();
    }

    public static <T> BodyDTO of(Boolean success, Integer code, T data) {
        return (new BodyBuilder(success, code, data)).build();
    }

    public String toString() {
        return "BodyDTO{ data=" + this.data + ", " + super.toString() + "} ";
    }

    private BodyDTO(BodyBuilder<T> builder) {
        super(builder);
        this.data = builder.data;
    }

    public static class BodyBuilder<T> extends HttpDTO.HttpBuilder {
        private final T data;

        protected BodyBuilder(T data) {
            super.success();
            this.data = data;
        }

        protected BodyBuilder(HttpStatus status, T data) {
            super.status(status);
            this.data = data;
        }

        protected BodyBuilder(Boolean success, Integer code, T data) {
            super.status(success, code);
            this.data = data;
        }

        public BodyDTO build() {
            return new BodyDTO(this);
        }

        protected BodyBuilder<T> self() {
            return this;
        }
    }
}
