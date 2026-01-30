package com.zy.map_project.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MapController {

    @GetMapping("/hello")
    public String hello(){
        return "Java Backend is Live in London";
    }
}
