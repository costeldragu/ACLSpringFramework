package com.mdc.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class HomeController {

    @GetMapping("/")
    public String indexPage(Model model) {
        return "index";
    }

    @GetMapping("/test")
    public String testPage(Principal principal, Model model) {
        model.addAttribute("username",principal.getName());
        return "test";
    }

}
