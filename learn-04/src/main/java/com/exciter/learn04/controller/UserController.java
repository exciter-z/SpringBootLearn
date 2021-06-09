package com.exciter.learn04.controller;

import com.exciter.learn04.bean.UserVo;
import com.exciter.learn04.constants.ServiceExceptionEnum;
import com.exciter.learn04.core.CommonResult;
import com.exciter.learn04.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping("/get")
    public UserVo get(@RequestParam("id") Integer id) {
        return new UserVo().setId(id).setUsername("username1:" + UUID.randomUUID().toString());
    }

    @GetMapping("/get2")
    public CommonResult<UserVo> get2(@RequestParam("id") Integer id) {
        UserVo user = new UserVo().setId(id).setUsername("username2:" + UUID.randomUUID().toString());
        return CommonResult.success(user);
    }

    @GetMapping("/exception-01")
    public UserVo exception01() {
        throw new NullPointerException("我裂开了");
    }

    @GetMapping("/exception-02")
    public UserVo exception02() {
        throw new ServiceException(ServiceExceptionEnum.USER_NOT_FOUND);
    }

    @GetMapping("/exception-03")
    public void exception03() {
        logger.info("[exception03]");
        throw new ServiceException(ServiceExceptionEnum.USER_NOT_FOUND);
    }

    @GetMapping("/do_something")
    public void doSomething() {
        logger.info("[doSomething]");
    }

    @GetMapping("/current_user")
    public UserVo currentUser() {
        logger.info("[currentUser]");
        return new UserVo().setId(10).setUsername(UUID.randomUUID().toString());
    }

    @PostMapping(value = "/add",
            consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public UserVo add(@RequestBody UserVo user) {
        return user;
    }


}
