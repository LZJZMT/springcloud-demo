package com.lzj.account.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: lzj
 * @Date： 2021/2/7 17:18
 */

@RestController
public class TestController {

    @GetMapping("/test")
    public String test(){
        return "test";
    }
}
