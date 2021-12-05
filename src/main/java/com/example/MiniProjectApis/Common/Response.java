package com.example.MiniProjectApis.Common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {

    private String message;
    private Integer status;
    private Object data;
    private MessageResponseDto[] errors;

    public Response(String message) {
        this.message = message;
    }

    public Response(String message, Integer status) {
        this.message = message;
        this.status = status;
    }

    public Response(String message, Integer status, Object data) {
        this.message = message;
        this.status = status;
        this.data = data;
    }

    public Response(String message, Object data) {
        this.message = message;
        this.data = data;
    }
}
