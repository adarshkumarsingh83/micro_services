package com.espark.adarsh.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ApplicationController {

    @RequestMapping("/**")
    public String forward() {
        return "forward:/actuator/info";
    }
}
