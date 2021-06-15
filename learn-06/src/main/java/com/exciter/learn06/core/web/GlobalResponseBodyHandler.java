package com.exciter.learn06.core.web;

import com.exciter.learn06.core.vo.CommonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.core.ReactiveAdapterRegistry;
import org.springframework.http.codec.HttpMessageWriter;
import org.springframework.web.reactive.HandlerResult;
import org.springframework.web.reactive.accept.RequestedContentTypeResolver;
import org.springframework.web.reactive.result.method.annotation.ResponseBodyResultHandler;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

public class GlobalResponseBodyHandler extends ResponseBodyResultHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalResponseBodyHandler.class);

    private static MethodParameter METHOD_PARAMETER_MONO_COMMON_RESULT;

    private static final CommonResult COMMON_RESULT_SUCCESS = CommonResult.success(null);

    static {
        try {
            METHOD_PARAMETER_MONO_COMMON_RESULT = new MethodParameter(
                    GlobalResponseBodyHandler.class.getDeclaredMethod("methodForParams"), -1
            );
        } catch (NoSuchMethodException e) {
            LOGGER.error("[static][获取 METHOD_PARAMETER_MONO_COMMON_RESULT 时，找不到方法]");
        }
    }

    public GlobalResponseBodyHandler(List<HttpMessageWriter<?>> writers, RequestedContentTypeResolver resolver) {
        super(writers, resolver);
    }

    public GlobalResponseBodyHandler(List<HttpMessageWriter<?>> writers, RequestedContentTypeResolver resolver, ReactiveAdapterRegistry registry) {
        super(writers, resolver, registry);
    }

    @Override
    public Mono<Void> handleResult(ServerWebExchange exchange, HandlerResult result) {
        return super.handleResult(exchange, result);
    }
}
