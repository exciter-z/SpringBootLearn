package com.exciter.learn04.controller;

import com.exciter.learn04.bean.UserVo;
import com.exciter.learn04.core.CommonResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping("/get")
    public UserVo get(@RequestParam("id") Integer id) {
        return new UserVo().setId(id).setUsername("username1:" + UUID.randomUUID().toString());
    }

    @GetMapping("/get2")
    public CommonResult<UserVo> get2(@RequestParam("id") Integer id) {
        UserVo user = new UserVo().setId(id).setUsername("username2:" + UUID.randomUUID().toString());
        return CommonResult.success(user);
    }
}
