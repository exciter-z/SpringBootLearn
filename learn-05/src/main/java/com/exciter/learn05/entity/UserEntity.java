package com.exciter.learn05.entity;

public class UserEntity {

    private Integer id;
    private String username;

    public Integer getId() {
        return id;
    }

    public UserEntity setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserEntity setUsername(String username) {
        this.username = username;
        return this;
    }
}
