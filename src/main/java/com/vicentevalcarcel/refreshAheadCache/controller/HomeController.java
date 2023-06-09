package com.vicentevalcarcel.refreshAheadCache.controller;

import com.vicentevalcarcel.refreshAheadCache.model.User;
import com.vicentevalcarcel.refreshAheadCache.service.UserService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HomeController {

    private final UserService userService;

    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public User index(){
        return new User(1);
    }
    @GetMapping("1")
    public List<User> getUser1(){
        System.out.println("call 1");
        return userService.findAllValidos();
    }

    @GetMapping("2")
    public List<User> getUser2(){
        System.out.println("call 2");
        return userService.findAllValidosWithParams("rand String",true);
    }
}
