package com.piclub.alpha.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/createActivity")
    public String getIndex() {
        return "mamami";
    }
}
