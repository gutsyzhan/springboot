package com.melody.limitredis.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;


@RestControllerAdvice
public class GlobalExceptionHandler {
    @ResponseBody
    @ExceptionHandler(BizException.class)
    public Map<String,Object> handler(BizException e){
        Map<String,Object> map = new HashMap<>();
        map.put("message",e.getMessage());
        map.put("code",e.getCode());
        return map;
    }
}
