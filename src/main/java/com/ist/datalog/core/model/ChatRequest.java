package com.ist.datalog.core.model;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author : LiYiFan
 * @date : 2023/10/30 16:25
 * @desc : chat问题参数
 */
public class ChatRequest {

    @ApiModelProperty("客户端发送的问题参数")
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
