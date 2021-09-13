package com.projects.proj2021.controllers;


import java.security.Principal;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class userLogin {
    @CrossOrigin(origins = "http://localhost:8080/")
    @GetMapping("/")
    public String greeting() {
        return "index";
    }

  /*  @ResponseBody
    @RequestMapping(value = "/user")
    public String user(Principal principal) {
        return "Hi "+principal.+" we got you verified";
    }*/

    @GetMapping("/user")
    public String userDetails(@AuthenticationPrincipal OAuth2User user, Model model) {
        model.addAttribute("name","Hi, "+user.getAttributes().get("name").toString());
        return "index";
    }
}
