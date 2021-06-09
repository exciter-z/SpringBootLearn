package com.exciter.learn04.controller;

import com.exciter.learn04.bean.UserVo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/test")
@CrossOrigin(origins = "*", allowCredentials = "true")
public class TestController {

    @GetMapping("/get")
    @CrossOrigin(allowCredentials = "false")
    public UserVo get() {
        return new UserVo().setId(1).setUsername(UUID.randomUUID().toString());
    }

}
