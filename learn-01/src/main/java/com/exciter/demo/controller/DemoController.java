package com.exciter.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DemoController {

    @GetMapping("/getVersion")
    public String getVersion(){
        return "{\n" +
                "    \"VersionName\":\"V1.0.1\",\n" +
                "    \"VersionCode\":101\n" +
                "}";
    }

}
