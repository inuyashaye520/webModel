package com.cn.idataitech.inuyasha.model.support;

public class JsonResult<T> {

    public static final int SUCCESS = 200;
    public static final JsonResult<NULL> NULL = JsonResult.success(null);

    private int code;
    private String message;
    private T data;

    public static <T> JsonResult<T> error(int code) {
        JsonResult<T> result = new JsonResult<>();
        result.setCode(code);
        return result;
    }

    public static <T> JsonResult<T> error(int code, String message) {
        JsonResult<T> result = new JsonResult<>();
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

    public static <T> JsonResult<T> error(Error error) {
        JsonResult<T> result = new JsonResult<>();
        result.setCode(error.getCode());
        result.setMessage(error.getMessage());
        return result;
    }

    public static <T> JsonResult<T> success(T data) {
        JsonResult<T> result = new JsonResult<>();
        result.setCode(SUCCESS);
        result.setData(data);
        return result;
    }

    public static <T> JsonResult<T> success(String message, T data) {
        JsonResult<T> result = new JsonResult<>();
        result.setCode(SUCCESS);
        result.setMessage(message);
        result.setData(data);
        return result;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return code == SUCCESS;
    }

    public static class NULL {
    }
}
