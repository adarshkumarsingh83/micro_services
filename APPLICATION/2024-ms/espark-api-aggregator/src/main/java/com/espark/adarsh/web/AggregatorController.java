package com.espark.adarsh.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AggregatorController {

    @Value("${application.message}")
    String message;

    @GetMapping({"/wish", "/greet"})
    public String greet() {
        return message+" AggregatorController";
    }
}
