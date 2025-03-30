package com.zeno.hsbc.vo;


import lombok.Data;

import java.io.Serializable;

@Data
public class Response <T> implements Serializable {

    private T data;

    private String message;

    private Integer code;

    public static <T> Response<T> success(T data) {
        Response<T> resp = new Response<>();
        resp.code = 0;
        resp.data = data;
        resp.message = "success";
        return resp;
    }

    public static <T> Response<T> fail(Integer code, String msg ) {
        Response<T> resp = new Response<>();
        resp.code = code;
        resp.data = null;
        resp.message = msg;
        return resp;
    }

}
