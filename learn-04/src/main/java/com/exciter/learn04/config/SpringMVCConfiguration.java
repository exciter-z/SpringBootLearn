package com.exciter.learn04.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.exciter.learn04.core.interceptor.FirstInterceptor;
import com.exciter.learn04.core.interceptor.SecondInterceptor;
import com.exciter.learn04.core.interceptor.ThirdInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
public class SpringMVCConfiguration implements WebMvcConfigurer {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Bean
    public FirstInterceptor firstInterceptor() {
        return new FirstInterceptor();
    }

    @Bean
    public SecondInterceptor secondInterceptor() {
        return new SecondInterceptor();
    }

    @Bean
    public ThirdInterceptor thirdInterceptor() {
        return new ThirdInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.firstInterceptor()).addPathPatterns("/**");
        registry.addInterceptor(this.secondInterceptor()).addPathPatterns("/users/current_user");
        registry.addInterceptor(this.thirdInterceptor()).addPathPatterns("/**");
    }

    @Bean
    public ServletRegistrationBean<?> testServlet01() {
        ServletRegistrationBean<?> servletRegistrationBean = new ServletRegistrationBean<>(new HttpServlet() {
            @Override
            protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                logger.info("[doGet][uri:{}]", req.getRequestURI());
            }
        });
        servletRegistrationBean.setUrlMappings(Collections.singleton("/test/01"));
        return servletRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean<?> testFilter01() {
        FilterRegistrationBean<?> filterRegistrationBean = new FilterRegistrationBean<>((servletRequest, servletResponse, filterChain) -> {
            logger.info("[doFilter]");
            filterChain.doFilter(servletRequest, servletResponse);
        });
        filterRegistrationBean.setUrlPatterns(Collections.singleton("/test/*"));
        return filterRegistrationBean;
    }

    @Bean
    public ServletListenerRegistrationBean<?> testListener01() {
        return new ServletListenerRegistrationBean<>(new ServletContextListener() {
            @Override
            public void contextInitialized(ServletContextEvent sce) {
                logger.info("[contextInitialized]");
            }

            @Override
            public void contextDestroyed(ServletContextEvent sce) {

            }
        });
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        //增加XML消息转换器
//        Jackson2ObjectMapperBuilder xmlBuilder = Jackson2ObjectMapperBuilder.xml();
//        xmlBuilder.indentOutput(true);
//        converters.add(new MappingJackson2XmlHttpMessageConverter(xmlBuilder.build()));
        //使用fastjson
        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setCharset(Charset.defaultCharset());
        fastJsonConfig.setSerializerFeatures(SerializerFeature.DisableCircularReferenceDetect);
        fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
        fastJsonHttpMessageConverter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON,
                MediaType.APPLICATION_JSON_UTF8));
        converters.add(0, fastJsonHttpMessageConverter);
    }


}
