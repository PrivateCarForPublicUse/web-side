package com.training.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PageTestController {
    @GetMapping("/page")
    public String a(){
        return "aa";
    }
}
