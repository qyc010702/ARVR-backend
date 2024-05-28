package com.ist.datalog.core;

import com.ist.datalog.core.exception.HandlerException;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;

/**
 * @author : LiYiFan
 * @date : 2023/10/25 16:07
 * @desc :
 */
public class HttpDTO <T extends Serializable> extends AbstractHttpDTO{

    public static <T> HttpDTO success() {
        return (new HttpBuilder()).build();
    }

    public static <T extends HandlerException> HttpDTO<T> error(T hex) {
        return (new HttpBuilder(hex)).build();
    }

    public static <T extends Exception> HttpDTO<T> error(T ex) {
        if (ex instanceof HandlerException) {
            return (new HttpBuilder((HandlerException)ex)).build();
        } else if (ex instanceof IOException) {
            return (new HttpBuilder(HttpStatus.BAD_REQUEST)).error("系统执行文件读写操作时发生错误", ex).build();
        } else if (ex instanceof NoSuchFieldException) {
            return (new HttpBuilder(HttpStatus.BAD_REQUEST)).error("加载数据时未找到指定参数或数据结构", ex).build();
        } else if (ex instanceof IllegalAccessException) {
            return (new HttpBuilder(HttpStatus.BAD_REQUEST)).error("数据正在被使用，当前无法访问，请稍后再试", ex).build();
        } else if (ex instanceof IllegalArgumentException) {
            return (new HttpBuilder(HttpStatus.BAD_REQUEST)).error("数据类型或格式不合法，无法被系统处理", ex).build();
        } else if (ex instanceof IndexOutOfBoundsException) {
            return (new HttpBuilder(HttpStatus.NOT_ACCEPTABLE)).error("可能因数据长度问题而导致了业务执行异常，请稍后重试或联系管理员处理", ex).build();
        } else if (ex instanceof NullPointerException) {
            return (new HttpBuilder(HttpStatus.NOT_ACCEPTABLE)).error("因数据有效性问题而导致了业务执行异常，请联系管理员处理", ex).build();
        } else {
            return ex instanceof SQLException ? (new HttpBuilder(HttpStatus.NOT_ACCEPTABLE)).error("数据库SQL语句执行失败，请稍后重试或联系管理员处理", ex).build() : (new HttpBuilder(HttpStatus.SERVICE_UNAVAILABLE)).error("服务异常，请联系管理员处理", ex).build();
        }
    }

    public static <T extends HandlerException> HttpDTO<T> error(HttpStatus status, T ex) {
        return ((HttpBuilder)(new HttpBuilder(status)).error(ex)).build();
    }

    public static <T> HttpDTO of(HttpStatus status) {
        return (new HttpBuilder(status)).build();
    }

    public static <T> HttpDTO of(Boolean success, Integer code) {
        return ((HttpBuilder)(new HttpBuilder()).status(success, code)).build();
    }

    public String toString() {
        return "HttpDTO{ " + super.toString() + " }";
    }

    protected HttpDTO(HttpBuilder<T> builder) {
        super(builder);
    }

    public static class HttpBuilder<T extends Serializable> extends AbstractHttpDTO.AbstractHttpBuilder<HttpBuilder<T>> {
        public HttpBuilder() {
            super.success();
        }

        public HttpBuilder(HttpStatus status) {
            super.status(status);
        }

        public HttpBuilder(HandlerException hex) {
            super.error(hex);
        }

        public HttpBuilder<T> error(String message, Exception ex) {
            super.error(message, ex);
            return this.self();
        }

        public HttpDTO<T> build() {
            return new HttpDTO(this);
        }

        protected HttpBuilder<T> self() {
            return this;
        }
    }
}
