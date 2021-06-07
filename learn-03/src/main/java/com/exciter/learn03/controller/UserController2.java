package com.exciter.learn03.controller;

import com.exciter.learn03.bean.UserVO;
import com.exciter.learn03.dto.UserAddDTO;
import com.exciter.learn03.dto.UserUpdateDTO;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users2")
public class UserController2 {

    @GetMapping("/list")
    public List<UserVO> list() {
        List<UserVO> result = new ArrayList<>();
        result.add(new UserVO().setId(1).setUsername("刘玄德"));
        result.add(new UserVO().setId(2).setUsername("关云长"));
        result.add(new UserVO().setId(3).setUsername("张翼德"));
        return result;
    }

    @GetMapping("/get")
    public UserVO get(@RequestParam("id") Integer id) {
        return new UserVO().setId(id).setUsername("username:" + id);
    }

    @PostMapping("/add")
    public Integer add(UserAddDTO addDTO) {
        Integer returnId = UUID.randomUUID().hashCode();
        return returnId;
    }

    @PostMapping("/update")
    public Boolean update(UserUpdateDTO updateDTO) {
        Boolean success = true;
        return success;
    }

    @DeleteMapping("/delete")
    public Boolean delete(@RequestParam("id") Integer id) {
        Boolean success = false;
        return success;
    }

}
