package com.ist.datalog.core;

import com.ist.datalog.core.exception.HandlerException;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;

import java.util.Objects;

/**
 * @author : LiYiFan
 * @date : 2023/10/25 15:44
 * @desc : 通用抽象响应结构
 */
public abstract class AbstractHttpDTO {

    @ApiModelProperty("网络状态")
    private final Integer code;
    @ApiModelProperty("返回结果")
    private final Boolean success;
    @ApiModelProperty("错误信息")
    private final String error;
    @ApiModelProperty("错误消息")
    private final String message;

    AbstractHttpDTO(AbstractHttpBuilder<?> builder) {
        this.code = builder.code;
        this.success = builder.success;
        this.error = builder.error;
        this.message = builder.message;
    }

    public Integer getCode() {
        return this.code;
    }

    public Boolean getSuccess() {
        return this.success;
    }

    public String getError() {
        return this.error;
    }

    public String getMessage() {
        return this.message;
    }

    public String toString() {
        return "AbstractHttpDTO{code=" + this.code + ", success=" + this.success + ", error='" + this.error + '\'' + ", message='" + this.message + '\'' + '}';
    }

    public abstract static class AbstractHttpBuilder<T extends AbstractHttpBuilder<T>> {
        private Boolean success;
        private Integer code;
        private String error = "";
        private String message = "";

        public AbstractHttpBuilder() {
        }

        protected T success(Boolean success) {
            if (Boolean.TRUE.equals(success)) {
                this.success = Boolean.TRUE;
            } else {
                this.success = Boolean.FALSE;
            }

            return this.self();
        }

        protected T code(Integer code) {
            this.code = code;
            return this.self();
        }

        protected T error(String error) {
            this.error = error;
            return this.self();
        }

        protected T message(String message) {
            this.message = message;
            return this.self();
        }

        public T success() {
            return this.success(Boolean.TRUE).code(HttpStatus.OK.value()).self();
        }

        public T status(HttpStatus status) {
            return this.success(!status.isError()).code(status.value()).self();
        }

        public T status(Boolean success, Integer code) {
            return this.success(success).code(code).self();
        }

        public T error(HandlerException hex) {
            this.success(Boolean.FALSE);
            this.code(hex.getCode());
            if (Objects.nonNull(hex.getEx())) {
                this.error(hex.getEx().getMessage());
            }

            this.message(hex.getMessage());
            return this.self();
        }

        public T error(Exception ex) {
            return this.error(ex.getMessage(), ex);
        }

        public T error(String message, Exception ex) {
            this.success(Boolean.FALSE);
            if (StringUtils.isEmpty(message)) {
                this.message(ex.getMessage());
            } else {
                this.message(message);
            }

            if (Objects.nonNull(ex.getCause())) {
                this.error(ex.getCause().toString());
            }

            return this.self();
        }

        protected abstract T self();
    }
}
