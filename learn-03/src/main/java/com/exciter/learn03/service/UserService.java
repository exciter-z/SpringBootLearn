package com.exciter.learn03.service;

import com.exciter.learn03.bean.UserVO;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    public UserVO get(Integer id){
        return new UserVO().setId(id).setUsername("test");
    }


}
