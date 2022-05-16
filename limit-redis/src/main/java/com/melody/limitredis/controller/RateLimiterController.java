package com.melody.limitredis.controller;

import com.melody.limitredis.annotations.RateLimiter;
import com.melody.limitredis.enums.LimitType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class RateLimiterController {
    @GetMapping("/test")
    @RateLimiter(time = 2,count = 5,limitType = LimitType.IP)
    public String test(){
        return "test >>>" +new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }
}
