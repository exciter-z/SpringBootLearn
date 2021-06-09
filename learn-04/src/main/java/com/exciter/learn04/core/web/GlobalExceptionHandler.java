package com.exciter.learn04.core.web;

import com.exciter.learn04.constants.ServiceExceptionEnum;
import com.exciter.learn04.core.CommonResult;
import com.exciter.learn04.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


@ControllerAdvice(basePackages = "com.exciter.learn04.controller")
public class GlobalExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 处理ServiceException异常
     */
    @ResponseBody
    @ExceptionHandler(value = ServiceException.class)
    public CommonResult<?> serviceExceptionHandler(HttpServletRequest request, ServiceException e) {
        logger.debug("[serviceExceptionHandler]", e);
        //包装CommonResult结果
        return CommonResult.error(e.getCode(), e.getMessage());
    }

    /**
     * 处理MissingServletRequestParameterException异常
     */
    @ResponseBody
    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public CommonResult<?> missingServletRequestParameterException(HttpServletRequest request, MissingServletRequestParameterException e) {
        logger.debug("[missingServletRequestParameterException]", e);
        return CommonResult.error(ServiceExceptionEnum.MISSING_REQUEST_PARAM_ERROR.getCode(),
                ServiceExceptionEnum.MISSING_REQUEST_PARAM_ERROR.getMessage());
    }

    /**
     * 处理其他异常
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public CommonResult<?> exceptionHandler(HttpServletRequest request, Exception e) {
        logger.debug("[exceptionHandler]", e);
        return CommonResult.error(ServiceExceptionEnum.SYS_ERROR.getCode(), ServiceExceptionEnum.SYS_ERROR.getMessage());
    }

}
