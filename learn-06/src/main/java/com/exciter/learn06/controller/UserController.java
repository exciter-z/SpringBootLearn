package com.exciter.learn06.controller;

import com.exciter.learn06.core.vo.CommonResult;
import com.exciter.learn06.core.vo.UserVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping("/list")
    public Flux<UserVo> list() {
        List<UserVo> result = new ArrayList<>();
        result.add(new UserVo().setId(1).setUsername("Q"));
        result.add(new UserVo().setId(2).setUsername("P"));
        result.add(new UserVo().setId(3).setUsername("E"));
        return Flux.fromIterable(result);
    }

    @GetMapping("/get")
    public Mono<UserVo> get(@RequestParam("id") Integer id) {
        UserVo user = new UserVo().setId(id).setUsername("username:" + id);
        return Mono.just(user);
    }

    @GetMapping("/get2")
    public Mono<CommonResult<UserVo>> get2(@RequestParam("id") Integer id) {
        UserVo user = new UserVo().setId(id).setUsername("username:" + id);
        return Mono.just(CommonResult.success(user));
    }

    @GetMapping("/get3")
    public UserVo get3(@RequestParam("id") Integer id) {
        UserVo user = new UserVo().setId(id).setUsername("username:" + id);
        return user;
    }

    @GetMapping("/get4")
    public CommonResult<UserVo> get4(@RequestParam("id") Integer id) {
        UserVo user = new UserVo().setId(id).setUsername("username:" + id);
        return CommonResult.success(user);
    }
}
