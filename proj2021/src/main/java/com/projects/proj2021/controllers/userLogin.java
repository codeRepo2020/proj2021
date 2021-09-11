package com.projects.proj2021.controllers;


import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class userLogin {

    @GetMapping("/")
    public String greeting() {
        return "index";
    }
}
