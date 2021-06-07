package com.exciter.learn04.core.web;

import com.exciter.learn04.core.CommonResult;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice(basePackages = "com.exciter.learn04.controller")
public class GlobalResponseBodyHandler implements ResponseBodyAdvice {

    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        //表示拦截Controller所有API接口返回的结果
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        //已经是CommonResult类型，直接返回
        if (o instanceof CommonResult) {
            return o;
        }
        //不是CommonResult,则包装成CommonResult
        return CommonResult.success(o);
    }
}
