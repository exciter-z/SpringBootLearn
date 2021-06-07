package com.exciter.learn03.controller;

import com.exciter.learn03.bean.UserVO;
import com.exciter.learn03.dto.UserAddDTO;
import com.exciter.learn03.dto.UserUpdateDTO;
import com.exciter.learn03.service.UserService;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 查询用户列表
     *
     * @return 用户列表
     */
    @GetMapping("")
    public List<UserVO> list() {
        List<UserVO> result = new ArrayList<>();
        result.add(new UserVO().setId(1).setUsername("陈仲甫"));
        result.add(new UserVO().setId(2).setUsername("李守常"));
        result.add(new UserVO().setId(3).setUsername("胡适之"));
        return result;
    }

    /**
     * 根据指定用户编号查询用户
     *
     * @param id 用户编号
     * @return 用户
     */
    @GetMapping("/{id}")
    public UserVO get(@PathVariable("id") Integer id) {
        return new UserVO().setId(id).setUsername("userName:" + id);
    }

    @GetMapping("/v2/{id}")
    public UserVO get2(@PathVariable("id") Integer id) {
        return userService.get(id);
    }

    /**
     * 添加用户
     *
     * @param addDTO 添加用户信息
     * @return 添加成功的用户编号
     */
    @PostMapping("")
    public Integer add(UserAddDTO addDTO) {
        //插入用户记录，返回编号
        Integer returnId = 1;
        //返回用户编号
        return returnId;
    }

    /**
     * 根据用户编号更新指定用户
     *
     * @param id        用户编号
     * @param updateDTO 更新用户信息
     * @return 是否更新成功
     */
    @PutMapping("/{id}")
    public Boolean update(@PathVariable("id") Integer id, UserUpdateDTO updateDTO) {
        //将id设置到updateDTO中
        updateDTO.setId(id);
        //更新用户记录
        Boolean success = true;
        //返回更新是否成功
        return success;
    }

    /**
     * 删除指定用户
     *
     * @param id 用户编号
     * @return 是否删除成功
     */
    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable("id") Integer id) {
        Boolean success = true;
        return success;
    }
}
