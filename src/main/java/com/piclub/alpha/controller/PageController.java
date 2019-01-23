package com.piclub.alpha.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
    @GetMapping("/activity/create")
    public String getBlankActivity() {
        return "createAct";
    }

    @GetMapping("/activity/{actId}/edit")
    public String getFilledActivity() {
        return "modifyAct";
    }

    @GetMapping("/activity/{actId}/view")
    public String getActivityPage() {
        return "actView";
    }
}
