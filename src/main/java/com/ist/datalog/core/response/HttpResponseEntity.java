package com.ist.datalog.core.response;

import com.ist.datalog.core.AbstractHttpDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * @author : LiYiFan
 * @date : 2023/10/25 19:47
 * @desc : 通用响应体结构
 */
public class HttpResponseEntity<T extends AbstractHttpDTO> extends ResponseEntity<T> {

    public HttpResponseEntity(T body, HttpStatus status) {
        super(body, status);
    }

    public static <T extends AbstractHttpDTO> ResponseEntity<T> of(T body) {
        return status(HttpStatus.OK).body(body);
    }

    public static <T extends AbstractHttpDTO> ResponseEntity<T> of(T body, HttpStatus status) {
        return status(status).body(body);
    }
}
