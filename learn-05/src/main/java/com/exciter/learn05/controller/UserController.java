package com.exciter.learn05.controller;

import com.exciter.learn05.dto.UserAddDTO;
import com.exciter.learn05.dto.UserUpdateDTO;
import com.exciter.learn05.entity.UserEntity;
import org.reactivestreams.Publisher;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping("/list")
    public Flux<UserEntity> list() {
        List<UserEntity> result = new ArrayList<>();
        result.add(new UserEntity().setId(1).setUsername("A"));
        result.add(new UserEntity().setId(2).setUsername("B"));
        result.add(new UserEntity().setId(3).setUsername("C"));
        return Flux.fromIterable(result);
    }

    @GetMapping("/get")
    public Mono<UserEntity> get(@RequestParam("id") Integer id) {
        UserEntity userEntity = new UserEntity().setId(id).setUsername("username:" + id);
        return Mono.just(userEntity);
    }

    @PostMapping("/add")
    public Mono<Integer> add(@RequestBody Publisher<UserAddDTO> addDTO) {
        Integer returnId = 1;
        return Mono.just(returnId);
    }

    @PostMapping("/add2")
    public Mono<Integer> add(Mono<UserAddDTO> addDTO) {
        Integer returnId = UUID.randomUUID().hashCode();
        return Mono.just(returnId);
    }

    @PostMapping("/update")
    public Mono<Boolean> update(@RequestBody Publisher<UserUpdateDTO> updateDTO) {
        Boolean success = true;
        return Mono.just(success);
    }

    @PostMapping("/delete")
    public Mono<Boolean> delete(@RequestParam("id") Integer id) {
        Boolean success = false;
        return Mono.just(success);
    }
}
