package com.espark.adarsh.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AggregatorController {

    @GetMapping({"/wish", "/greet"})
    public String greet() {
        return "welcome to espark";
    }
}
