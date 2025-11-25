package com.cohort3.week2.springmvcRESTAPI.Week2L_4ServiceLayer.advices;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@RestControllerAdvice
public class GlobalResponseHandler implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    } //this method tell that do you support the return type and converterType to convert and use the method beforeBodyWrite
    // for false we can apply check for each returnType
    //for true support the conversion of each and every API response type

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {

        if(body instanceof ApiResponse<?>)
            return body;

        return new ApiResponse<>(body);
    }
    //it gives use the object body and gives us the way to transform the body into any thing
   // so before returning it encapsulate the body into any other return type so that the return will always uniform throughout the code
}
