package com.example.MovieList.Timer;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * This class is created to add header (X-TIME-TO-EXECUTE) in response, i.e. How much time the api takes to execute
 */
@ControllerAdvice
public class GeneralControllerAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        ServletServerHttpRequest servletServerRequest = (ServletServerHttpRequest) request;
        long startTime = (long) servletServerRequest.getServletRequest().getAttribute("startTime");
        long timeElapsed = System.currentTimeMillis() - startTime;
        response.getHeaders().add("X-TIME-TO-EXECUTE", String.valueOf(timeElapsed));
        return body;
    }
}