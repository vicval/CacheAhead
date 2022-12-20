package com.vicentevalcarcel.refreshAheadCache.controller;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class HomeController {

    @GetMapping
    @Cacheable(value = "index")
    public String index(){
        System.out.println("executou");
        return "teste";
    }
}
