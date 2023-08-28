package com.acadiasoft.simplehttpapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/index.html")
    public String getIndex() {
        return "index";
    }
}
