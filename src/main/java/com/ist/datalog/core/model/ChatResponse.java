package com.ist.datalog.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author : LiYiFan
 * @date : 2023/10/30 16:33
 * @desc :
 */
public class ChatResponse {

    @JsonProperty("question_tokens")
    @ApiModelProperty("问题消耗tokens")
    private long questionTokens = 0;

    public long getQuestionTokens() {
        return questionTokens;
    }

    public void setQuestionTokens(long questionTokens) {
        this.questionTokens = questionTokens;
    }
}
