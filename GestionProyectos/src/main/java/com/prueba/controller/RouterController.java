package com.prueba.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class RouterController {
    
    @GetMapping("/")
    public String home(@RequestParam String param) {
        return "home";
    } 
}
