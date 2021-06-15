package com.exciter.learn06.core.vo;

public class UserVo {
    private Integer id;
    private String username;

    public Integer getId() {
        return id;
    }

    public UserVo setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserVo setUsername(String username) {
        this.username = username;
        return this;
    }
}
