package com.prueba.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class RouterController {
    
    @GetMapping("/")
    public String home() {
        return "home";  
    }
    
    @GetMapping("/graphs")
    public String graphs() {
        return "graphs";
    }

    @GetMapping("/projectAnalyzer")
    public String projectAnalyzer(){
        return "projectAnalyzer";
    }
}
