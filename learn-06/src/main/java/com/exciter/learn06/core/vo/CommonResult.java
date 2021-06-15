package com.exciter.learn06.core.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.util.Assert;

import java.io.Serializable;

public class CommonResult<T> implements Serializable {

    public static Integer CODE_SUCCESS = 0;

    /**
     * 错误码
     */
    private Integer code;

    /**
     * 错误提示
     */
    private String message;

    /**
     * 返回数据
     */
    private T data;

    public static <T> CommonResult<T> error(CommonResult<?> result) {
        return error(result.code, result.message);
    }

    public static <T> CommonResult<T> error(Integer code, String message) {
        Assert.isTrue(!CODE_SUCCESS.equals(code), "code 必须是错误的!");
        CommonResult<T> result = new CommonResult<>();
        result.code = code;
        result.message = message;
        return result;
    }

    public static <T> CommonResult<T> success(T data) {
        CommonResult<T> result = new CommonResult<>();
        result.code = CODE_SUCCESS;
        result.data = data;
        result.message = "success";
        return result;
    }

    @JsonIgnore
    public boolean isSuccess() {
        return CODE_SUCCESS.equals(code);
    }

    @JsonIgnore
    public boolean isError() {
        return !isSuccess();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
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
}
